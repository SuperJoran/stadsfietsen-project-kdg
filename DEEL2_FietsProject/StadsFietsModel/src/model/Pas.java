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
@Table(name = "t_pas")
public class Pas {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String wachtwoord;
    private Date beginDatum;
    private Date eindDatum;

    @ManyToOne
    @JoinColumn(name = "pasTypeId", nullable = false)
    private PasType pasType;

    @ManyToOne
    @JoinColumn(name="fietsTypeId", nullable = false)
    private FietsType fietsType;

    @OneToMany(mappedBy = "pas")
    private Set<Rit> ritten = new HashSet<Rit>();

    @ManyToOne
    @JoinColumn(name="verkoopId", nullable = false)
    private Verkoop verkoop;

    public Pas() {
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Date getBeginDatum() {
        return beginDatum;
    }

    public void setBeginDatum(Date beginDatum) {
        this.beginDatum = beginDatum;
    }

    public Date getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(Date eindDatum) {
        this.eindDatum = eindDatum;
    }

    public PasType getPasType() {
        return pasType;
    }

    public void setPasType(PasType pasType) {
        this.pasType = pasType;
    }

    public FietsType getFietsType() {
        return fietsType;
    }

    public void setFietsType(FietsType fietsType) {
        this.fietsType = fietsType;
    }

    public Set<Rit> getRitten() {
        return ritten;
    }

    public void voegRitToe(Rit rit) {
        this.ritten.add(rit);
    }

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }
}
