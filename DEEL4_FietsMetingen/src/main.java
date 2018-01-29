import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * Created by user Jorandeboever
 * Date:31/10/14.
 */
public class main {
    public static void main(String[] args) {
        MetingGenerator generator = new MetingGenerator();
        try {
            generator.generateRows(generator.getDatabase());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
