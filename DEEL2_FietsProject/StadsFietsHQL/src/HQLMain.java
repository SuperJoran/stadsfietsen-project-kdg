import model.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by user user Jorandeboever
 * Date:date:25/10/14.
 */
public class HQLMain {
    public static void main(String[] args) {
        HQLStadsFietsService hqlStadsFietsService = new HQLStadsFietsService();

        /*
         * Console app om de hqlStadsFietsService te testen
         */
        Scanner scanner = new Scanner(System.in);
        int keuze = 4;
        while (keuze != 0) {
            System.out.println("Wat wilt u weten? (0 to exit)");
            System.out.println("1. Aantal pasjes die verkocht zijn sinds een datum (1)");
            System.out.println("2. Alle afgelegen stations met e-bikes (2)");
            System.out.println("3. Stations met meer dan bepaald aantal vrije plaatsen (3)");
            keuze = scanner.nextInt();
            if (keuze == 1) {
                System.out.println("Geef datum: (dd/mm/yyyy)");
                scanner.nextLine();
                try {
                    String datumString = scanner.nextLine();
                    Date datum = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse(datumString);
                    System.out.printf("Er zijn %d pasjes verkocht sinds %s\n", hqlStadsFietsService.geefVerkochtePasjes(datum), datum);
                } catch (ParseException e) {
                    System.err.println("Verkeerd formaat!");
                }
            } else if (keuze == 2) {
                List<Station> stations = hqlStadsFietsService.geefAfgelegenStations();
                System.out.println("Afgelegen stations met e-bikes:");
                for (Station station : stations) {
                    System.out.println(station);
                }
            } else if (keuze == 3) {
                System.out.println("Geef aantal vrije sloten: ");
                int aantal = scanner.nextInt();
                List<Station> stations = hqlStadsFietsService.geefOnderbezetteStations(aantal);
                System.out.println("Onderbezette stations");
                for (Station station : stations) {
                    System.out.println(station);
                }
            }
            System.out.println("--------------------------------------------");

        }
 //       session.close();


    }


}
