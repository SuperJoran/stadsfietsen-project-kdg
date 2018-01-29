package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_station")
public class Station {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String karakteristiek;

    @OneToMany(mappedBy = "station")
    private Set<Slot> sloten = new HashSet<Slot>();

    @ManyToOne
    @JoinColumn (name = "stadId", nullable = false)
    private Stad stad;

    public Station() {
    }

    public String getKarakteristiek() {
        return karakteristiek;
    }

    public void setKarakteristiek(String karakteristiek) {
        this.karakteristiek = karakteristiek;
    }

    public Stad getStad() {
        return stad;
    }

    public void setStad(Stad stad) {
        this.stad = stad;
    }

    @Override
    public String toString() {
        String stationString = "";
        if(karakteristiek != null){
            stationString +=  "Station: " + Id + " (" + karakteristiek + ")" ;
        }else {
            stationString +=  "Station: " + Id;

        }
        return stationString;
    }
}
