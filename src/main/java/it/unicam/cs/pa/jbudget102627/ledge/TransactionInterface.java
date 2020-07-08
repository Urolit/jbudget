package it.unicam.cs.pa.jbudget102627.ledge;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface TransactionInterface {

    //getters
    int getId();
    List<TagInterface> getTags();
    LocalDate getDate();
    List<MovementInterface> getMovements();
    List<MovementInterface> getMovements(Predicate<MovementInterface> p);


    //setters
    void setId(int id);
    void setTags(List<TagInterface> l);
    void setDate(LocalDate d);

    //other
    double getTotalAmount();
    void addTag(TagInterface c);
    boolean rmTag(TagInterface c);
    void addMovement(MovementInterface m);
    boolean rmMovement(MovementInterface m);
    boolean rmMovement(Predicate<MovementInterface> p);


}