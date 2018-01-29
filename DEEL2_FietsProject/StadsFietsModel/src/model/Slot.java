package model;

import javax.persistence.*;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_slot")
public class Slot {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "fietsId")
    private Fiets fiets;

    @ManyToOne
    @JoinColumn(name="stationId", nullable = false)
    private Station station;

    public Slot() {
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Fiets getFiets() {
        return fiets;
    }

    public void setFiets(Fiets fiets) {
        this.fiets = fiets;
    }
}
