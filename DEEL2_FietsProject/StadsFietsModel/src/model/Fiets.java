package model;

import javax.persistence.*;
/**
 * Created by user Joran
 * Date:21-10-2014.
 */
@Entity
@Table(name = "t_fiets")
public class Fiets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name="fietsTypeId", nullable = false)
    private FietsType fietsType;

    @OneToOne (mappedBy = "fiets")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "stadId", nullable = false)
    private Stad stad;

    public Fiets() {
    }

    public FietsType getFietsType() {
        return fietsType;
    }

    public void setFietsType(FietsType fietsType) {
        this.fietsType = fietsType;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Stad getStad() {
        return stad;
    }

    public void setStad(Stad stad) {
        this.stad = stad;
    }
}
