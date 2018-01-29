package model;

import javax.persistence.*;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_fietstype")
public class FietsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String naam;

    private Double dagPrijs;

    public FietsType() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getDagPrijs() {
        return dagPrijs;
    }

    public void setDagPrijs(Double dagPrijs) {
        this.dagPrijs = dagPrijs;
    }
}
