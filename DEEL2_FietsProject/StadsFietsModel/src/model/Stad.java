package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_stad")
public class Stad {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String naam;

    private Integer postCode;

    @OneToMany(mappedBy = "stad")
    private Set<Station> stations = new HashSet<Station>();

    @OneToMany(mappedBy = "stad")
    private Set<Fiets> fietsen = new HashSet<Fiets>();

    public Stad() {
    }

    public Integer getId() {
        return Id;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }
}
