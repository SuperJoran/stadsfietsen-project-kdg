package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_rit")
public class Rit {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Date beginDatum;
    private Date eindDatum;

    @ManyToOne
    @JoinColumn(name="pasId", nullable = false)
    private Pas pas;

    @ManyToOne
    @JoinColumn(name="fietsId", nullable = false)
    private Fiets fiets;

    @ManyToOne
    @JoinColumn(name="beginStationId")
    private Station beginStation;

    @ManyToOne
    @JoinColumn(name="eindStationId")
    private Station eindStation;

    public Rit() {
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

    public Pas getPas() {
        return pas;
    }

    public void setPas(Pas pas) {
        this.pas = pas;
    }

    public Fiets getFiets() {
        return fiets;
    }

    public void setFiets(Fiets fiets) {
        this.fiets = fiets;
    }

    public Station getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(Station beginStation) {
        this.beginStation = beginStation;
    }

    public Station getEindStation() {
        return eindStation;
    }

    public void setEindStation(Station eindStation) {
        this.eindStation = eindStation;
    }
}
