package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_verkoop")
public class Verkoop {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Date datum;

    @ManyToOne
    @JoinColumn(name = "klantId", nullable = false)
    private Klant klant;

    @OneToMany(mappedBy = "verkoop")
    private Set<Pas> pasjes = new HashSet<Pas>();

    public Verkoop() {
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void voegPasToe(Pas pas){
        this.pasjes.add(pas);
    }

    public Set<Pas> getPasjes() {
        return pasjes;
    }
}
