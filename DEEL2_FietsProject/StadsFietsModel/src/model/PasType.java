package model;

import javax.persistence.*;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_pastype")
public class PasType {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String naam;

    public PasType() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
