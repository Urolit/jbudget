package it.unicam.cs.pa.jbudget102627.ledge;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LedgeTest {

    @Test
    void getTransactionsWithPredicate() {
        Ledge ledge = new Ledge();
        Transaction t1 = new Transaction(0, LocalDate.now());
        Transaction t2 = new Transaction(1,LocalDate.now());
        Account a = new Account(0,0,"aa","aa",AccountType.ASSET);
        TagInterface t = new Tag(0,"test");
        ledge.addTag(t);
        List<Integer> taglist = new ArrayList<>();
        taglist.add(t.getId());
        Movement m = new Movement(0,2,"",MovementType.CREDIT,taglist,t1.getDate(),a);

        t1.addMovement(m);
        t2.addMovement(m);


        ledge.addTransaction(t1);
        ledge.addTransaction(t2);
        assertTrue(ledge.getTransactions(x -> x.getId() > 5).isEmpty());
        assertTrue(ledge.getTransactions(x -> x.getDate()==null).isEmpty());
        assertEquals(ledge.getTransactions(x -> x.getId() == 1).size(),1);
    }

    @Test
    void rmTransaction() {
        Ledge ledge = new Ledge();
        Transaction t1 = new Transaction(0,LocalDate.now());
        Account a = new Account(0,0,"aa","aa",AccountType.ASSET);
        TagInterface t = new Tag(0,"test");
        ledge.addTag(t);
        List<Integer> taglist = new ArrayList<>();
        taglist.add(t.getId());
        Movement m = new Movement(0,2,"",MovementType.CREDIT,taglist,t1.getDate(),a);

        t1.addMovement(m);

        ledge.addTransaction(t1);
        assertFalse(ledge.getTransactions().isEmpty());
        ledge.rmTransaction(t1);
        assertTrue(ledge.getTransactions().isEmpty());

    }

    @Test
    void rmTag() {
        Ledge ledge = new Ledge();
        Transaction t1 = new Transaction(0,LocalDate.now());
        Account a = new Account(0,0,"aa","aa",AccountType.ASSET);
        TagInterface t = new Tag(0,"test");
        ledge.addTag(t);
        List<Integer> taglist = new ArrayList<>();
        taglist.add(t.getId());
        Movement m = new Movement(0,2,"",MovementType.CREDIT,taglist,t1.getDate(),a);
        t1.addMovement(m);

        t1.addTag(t);
        ledge.addTag(t);
        ledge.addTransaction(t1);
        assertFalse(t1.getTags().isEmpty());
        assertFalse(ledge.getTags().isEmpty());
        ledge.rmTag(t);
        assertTrue(ledge.getTags().isEmpty());
        assertTrue(t1.getTags().isEmpty());
    }

    @Test
    void rmAccount() {
        Ledge ledge = new Ledge();
        Transaction t1 = new Transaction(0,LocalDate.now());
        Account a = new Account(0,0,"aa","aa",AccountType.ASSET);
        TagInterface t = new Tag(0,"test");
        ledge.addTag(t);
        List<Integer> taglist = new ArrayList<>();
        taglist.add(t.getId());
        Movement m = new Movement(0,2,"",MovementType.CREDIT,taglist,t1.getDate(),a);
        t1.addMovement(m);
        ledge.addTransaction(t1);
        ledge.addAccount(a);
    }

}