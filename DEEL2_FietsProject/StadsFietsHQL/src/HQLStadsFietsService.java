import hqlconfiguration.HQLHibernateUtil;
import model.Slot;
import model.Station;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Created by user user Jorandeboever
 * Date:date:25/10/14.
 */
public class HQLStadsFietsService {
    /*
  * Methode 1 : Verkochte Pasjes
  * Geef het aantal pasjes terug dat verkocht is
  * sinds een gegeven tijdstip
  * gegroepeerd per pas type
  * */
    public Long geefVerkochtePasjes(Date datum) {
        Session session = HQLHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Pas p where p.verkoop.datum > :datum ");
        query.setTimestamp("datum", datum);
        Long aantal = (Long) query.uniqueResult();
        transaction.commit();
        return aantal;
    }

    /*
    * Methode 2: Afgelegen stations
    * Geef alle stations
    * met de karakteristiek ‘Afgelegen’
    * waar e-bikes zijn gestationeerd
    */
    public List<Station> geefAfgelegenStations() {
        Session session = HQLHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select station from Station station join station.sloten slot  where station.karakteristiek = 'Afgelegen' and slot.fiets.fietsType.naam = 'e-bike' group by station");
        Collection stationsCollection = query.list();
        transaction.commit();
        return (List) stationsCollection;
    }

    /*
   * Methode 3: Onderbezette stations
   * Geeft alle stations terug
   * waarvoor het aantal vrije slots hoger is dan een gegeven aantal
   * (zonder gebruik te maken van eventuele berekende velden in station of slot)
   */
    public List<Station> geefOnderbezetteStations(int aantal) {
        Session session = HQLHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select station from Station station join  station.sloten slot WHERE slot.fiets is null  group by station having count(slot) > :aantal ");
        query.setInteger("aantal", aantal);

        Collection stationsCollection = query.list();
        transaction.commit();
        return (List) stationsCollection;
    }

    public List<Slot> geefLegeSloten() {
        Session session = HQLHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" from Slot slot join slot.fiets  WHERE slot.fiets is null ");

        Collection stationsCollection = query.list();
        return (List) stationsCollection;
    }
}
