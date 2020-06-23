package it.unicam.cs.pa.jbudget.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Movement implements MovementInterface {

    private int id;
    private double value;
    private String motivation;
    private MovementType type;
    private List<Tag> tagList;
    private LocalDate date;
    private Account account;

    public Movement(int i, double val, String motiv, MovementType mt,
                    List<Tag> tag, LocalDate ld, Account a ){
        setId(i);
        setValue(val);
        setMotivation(motiv);
        setType(mt);
        setTags(tag);
        setDate(ld);
        setAccount(a);
    }

    @Override
    public int getId() {return this.id;}

    @Override
    public String getMotivation() {return this.motivation;}

    @Override
    public double getValue() {return this.value;}

    @Override
    public MovementType getType() {return this.type;}

    @Override
    public List<Tag> getTags() {return this.tagList;}

    @Override
    public LocalDate getDate() {return this.date;}

    @Override
    public Account getAccount() {return this.account;}

    @Override
    public void setId(int id) {this.id = id;}

    @Override
    public void setMotivation(String m) {this.motivation = m;  }

    @Override
    public void setValue(double d) {this.value = d;}

    @Override
    public void setType(MovementType mt) {this.type = mt;}

    @Override
    public void setTags(List<Tag> l) {this.tagList = l;}

    @Override
    public void setDate(LocalDate d) {this.date = d;}

    @Override
    public void setAccount(Account a) {this.account = a;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;
        Movement movement = (Movement) o;
        return getId() == movement.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public void addTag(Tag c) {
        if(!getTags().contains(c))
            getTags().add(c);
    }

    @Override
    public boolean rmTag(Tag c) {
        if(getTags().contains(c)){
            getTags().remove(c);
            return true;
        }else{
            return false;
        }
    }

}
