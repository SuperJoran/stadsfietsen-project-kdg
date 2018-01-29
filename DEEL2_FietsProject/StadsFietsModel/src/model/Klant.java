package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_klant")
public class Klant {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer postCode;

    private String naam;

    @OneToMany(mappedBy = "klant")
    private Set<Verkoop> verkopen = new HashSet<Verkoop>();

    public Klant() {
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
