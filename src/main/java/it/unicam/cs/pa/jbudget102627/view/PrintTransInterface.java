package it.unicam.cs.pa.jbudget102627.view;

import it.unicam.cs.pa.jbudget102627.Controller;
import it.unicam.cs.pa.jbudget102627.ledge.TransactionInterface;

public interface PrintTransInterface {

    void printTransaction(TransactionInterface tra, Controller controller);
    TransactionInterface addTransaction(Controller controller);
    int rmTransaction();
}
