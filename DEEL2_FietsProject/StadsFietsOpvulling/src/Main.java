import configuration.HibernateUtil;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import de.beimax.janag.NameGenerator;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
public class Main {

    public static void main(String[] args) {
        FietsType normaal = GenerateFietsType("normaal");
        FietsType ebike = GenerateFietsType("ebike");
        FietsType sport = GenerateFietsType("sport");
        Stad stad = GenerateStad();
        List<Station> stations = GenerateStations(stad);
        List<Fiets> fietsen = GenerateFietsen(stad, normaal, sport, ebike);
        List<Slot> sloten = GenerateSloten(stations, fietsen);
        List<Klant> klanten = GenerateKlanten();
        GeneratePasjes(klanten, fietsen, stations, normaal, sport, ebike);

    }

    public static Stad GenerateStad() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Stad stad = new Stad();
        stad.setNaam("Antwerpen");
        stad.setPostCode(2000);
        session.saveOrUpdate(stad);
        tx.commit();

        return stad;
    }

    public static List<Station> GenerateStations(Stad stad) {
        List<Station> stations = new ArrayList<Station>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < 30; i++) {
            Station station = new Station();
            station.setStad(stad);
            if (i % 3 == 0) {
                station.setKarakteristiek("Afgelegen");
            }
            session.saveOrUpdate(station);
            stations.add(station);
        }

        tx.commit();
        return stations;
    }

    public static List<Slot> GenerateSloten(List<Station> stations, List<Fiets> fietsen) {
        List<Slot> sloten = new ArrayList<Slot>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Random random = new Random();
        int getal = 0;
        for (Station station : stations) {
            int aantal = random.nextInt(20) + 10;
            for (int i = 0; i < aantal; i++) {
                Slot slot = new Slot();
                slot.setStation(station);
                session.saveOrUpdate(slot);
                sloten.add(slot);
                if (getal < 500) {
                    slot.setFiets(fietsen.get(getal++));
                }
            }
        }
        tx.commit();
        return sloten;
    }

    public static FietsType GenerateFietsType(String type) {

        FietsType fietsType = new FietsType();
        if (type.equals("ebike")) {
            fietsType.setNaam("e-bike");
            fietsType.setDagPrijs(6.00);
        } else if (type.equals("sport")) {
            fietsType.setNaam("sport");
            fietsType.setDagPrijs(4.50);
        } else {
            fietsType.setNaam("normaal");
            fietsType.setDagPrijs(3.00);
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(fietsType);
        tx.commit();
        return fietsType;
    }

    public static List<Fiets> GenerateFietsen(Stad stad, FietsType normaal, FietsType sport, FietsType eBike) {
        List<Fiets> fietsen = new ArrayList<Fiets>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(normaal);
        session.saveOrUpdate(sport);
        session.saveOrUpdate(eBike);

        for (int i = 1; i <= 500; i++) {
            Fiets fiets = new Fiets();
            fiets.setStad(stad);
            // fiets.setSlot(sloten.get(i));
            if (i % 100 == 0) {
                fiets.setFietsType(eBike);
            } else if (i < 96) {
                fiets.setFietsType(sport);
            } else {
                fiets.setFietsType(normaal);
            }
            fietsen.add(fiets);
            session.saveOrUpdate(fiets);
        }

        tx.commit();
        return fietsen;
    }

    public static List<Klant> GenerateKlanten() {
        List<Klant> klanten = new ArrayList<Klant>();

        Random random = new Random();
        // library die random namen voor ons genereert
        NameGenerator generator = new NameGenerator("languages.txt", "semantics.txt");
        String[] maleNames = generator.getRandomName("U.S. Human (1990)", "Male - Uncommon", 5000);
        String[] femaleNames = generator.getRandomName("U.S. Human (1990)", "Female - Uncommon", 5000);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        //mannelijke klanten
        for (int i = 0; i < 10000; i++) {
            Klant klant = new Klant();

            //helft klanten woont in Antwerpen
            if (i % 2 == 0) {
                klant.setPostCode(2000);
            } else {
                klant.setPostCode(1000 + random.nextInt(8000));
            }

            if (i < 5000) {
                klant.setNaam(maleNames[i]);
            } else {
                klant.setNaam(femaleNames[i - 5000]);
            }
            klanten.add(klant);
            session.saveOrUpdate(klant);
        }


        tx.commit();
        return klanten;
    }

    public static void GeneratePasjes(List<Klant> klanten, List<Fiets> fietsen, List<Station> stations, FietsType normaal, FietsType sport, FietsType ebike) {

        Random random = new Random();

        PasType dagPas = new PasType();
        dagPas.setNaam("dagpas");

        PasType weekPas = new PasType();
        weekPas.setNaam("weekpas");

        PasType jaarPas = new PasType();
        jaarPas.setNaam("jaarabonnement");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(dagPas);
        session.saveOrUpdate(weekPas);
        session.saveOrUpdate(jaarPas);
        tx.commit();

        for (int i = 0; i < 10000; i++) {
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2014"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Verkoop verkoop = new Verkoop();  // Maak verkoop object aan
            verkoop.setKlant(klanten.get(i));
            cal.add(Calendar.DATE, random.nextInt(363));
            verkoop.setDatum(cal.getTime());

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(verkoop);
            // Elke verkoop-object heeft 2 pasjes:
            Pas eerstePas = new Pas();      //eerste pas
            eerstePas.setBeginDatum(cal.getTime());
            eerstePas.setVerkoop(verkoop);
            Pas tweedePas = new Pas();      //tweede pas
            tweedePas.setVerkoop(verkoop);
            tweedePas.setBeginDatum(cal.getTime());
            if (i < 500) {
                cal.add(Calendar.DATE, 5);
                eerstePas.setPasType(weekPas);
                eerstePas.setFietsType(sport);
                tweedePas.setPasType(weekPas);
                tweedePas.setFietsType(sport);
            } else if (i < 3000) {
                eerstePas.setPasType(dagPas);
                eerstePas.setFietsType(ebike);
                tweedePas.setPasType(dagPas);
                tweedePas.setFietsType(ebike);
            } else {
                cal.add(Calendar.DATE, 365);
                eerstePas.setPasType(jaarPas);
                eerstePas.setFietsType(normaal);
                tweedePas.setPasType(jaarPas);
                tweedePas.setFietsType(normaal);
            }
            eerstePas.setEindDatum(cal.getTime());
            tweedePas.setEindDatum(cal.getTime());

            session.saveOrUpdate(eerstePas);
            session.saveOrUpdate(tweedePas);
            tx.commit();

            GenerateRitten(eerstePas, fietsen, stations);
            GenerateRitten(tweedePas, fietsen, stations);
        }

    }

    /**
     * Genereert Ritten voor 1 pas
     */
    public static void GenerateRitten(Pas pas, List<Fiets> fietsen, List<Station> stations) {
        Random random = new Random();
        // met jaarabonnementen worden gemiddeld 300 ritten gemaakt
        int aantal = 300;
        if (pas.getPasType().getNaam().equals("weekpas")) {
            // met weekpassen worden gemiddeld 10 ritten gemaakt
            aantal = 10;
        } else if (pas.getPasType().getNaam().equals("dagpas")) {
            // met dagpassen worden gemiddeld 2 ritten gemaakt
            aantal = 2;
        }

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        for (int i = 0; i < aantal; i++) {
            Rit rit = new Rit();
            rit.setBeginStation(stations.get(random.nextInt(stations.size())));
            rit.setEindStation(stations.get(random.nextInt(stations.size())));

            rit.setEindDatum(pas.getBeginDatum());
            rit.setFiets(fietsen.get(random.nextInt(fietsen.size())));
            rit.setPas(pas);


            Date beginDatum = pas.getBeginDatum();
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(beginDatum);

            if (pas.getPasType().getNaam().equals("jaarabonnement")) {
                //voeg random aantal dagen toe (klanten kunnen rit maken heel het jaar door)
                calendarDate.add(Calendar.DATE, random.nextInt(365));

            } else if (pas.getPasType().getNaam().equals("weekpas")) {
                //voeg random aantal dagen toe (klanten kunnen rit maken heel de week door)
                calendarDate.add(Calendar.DATE, random.nextInt(5));
            }
            // random begin-uur voor de rit: (ritten beginnen tussen 6 en 14 uur)
            calendarDate.add(Calendar.HOUR_OF_DAY, random.nextInt(8) + 6);
            rit.setBeginDatum(calendarDate.getTime());
            // voeg random aantal minuten toe voor de ritduur (15 tot 60 minuten)
            calendarDate.add(Calendar.MINUTE, random.nextInt(45) + 15);
            rit.setEindDatum(calendarDate.getTime());
            session.saveOrUpdate(rit);

        }
        tx.commit();

    }

}
