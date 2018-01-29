import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by user Jorandeboever
 * Date:31/10/14.
 */
public class MetingGenerator {
    public DB getDatabase() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        return mongoClient.getDB("local");
    }

    public void generateRows(DB db) throws ParseException {
        DBCollection collection = db.getCollection("meting");
        Random random = new Random();
        DecimalFormat f = new DecimalFormat("##.000");

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10000; i++) {
            calendar.setTime(new SimpleDateFormat("dd-MM-yyyy hh:mm").parse("01-10-2014 00:00"));
            calendar.add(Calendar.MINUTE, random.nextInt(1440));

            BasicDBObject document = new BasicDBObject("stad", getRandomStad())
                    .append("fietsId", random.nextInt(500))
                    .append("datum", calendar.getTime())
                    .append("meting", getRandomMeting())
                    .append("loc", getRandomCoordinates());

            collection.insert(document);
        }
    }

    private BasicDBObject getRandomCoordinates() {
        BasicDBObject loc = new BasicDBObject("type", "Point");

        Random random = new Random();
        Double[] coordinates = new Double[2];
        coordinates[1] = round( 51 + (random.nextDouble()), 3);
        coordinates[0] = round( 3 + (random.nextDouble() * (2)),3);
        loc.append("coordinates", coordinates);
        return loc;
    }

    public BasicDBObject getRandomMeting() {
        Random random = new Random();
        int aantalParameters = random.nextInt(3) + 1;
        BasicDBObject meting = new BasicDBObject("co2", random.nextInt(100));
        if (aantalParameters > 1) {
            meting.append("relatieve_vochtigheid", random.nextInt(100));
        }
        if (aantalParameters == 3) {
            meting.append("fijn_stof", random.nextInt(100));
        }

        return meting;
    }

    private String getRandomStad() {
        Random random = new Random();
        String stad;
        int getal = random.nextInt(3);
        switch (getal) {
            case 1:
                stad = "Mechelen";
                break;
            case 2:
                stad = "Gent";
                break;
            default:
                stad = "Antwerpen";
        }
        return stad;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
