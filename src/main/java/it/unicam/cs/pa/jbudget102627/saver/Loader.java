package it.unicam.cs.pa.jbudget102627.saver;

import com.google.gson.Gson;
import it.unicam.cs.pa.jbudget102627.Controller;
import it.unicam.cs.pa.jbudget102627.IDManager;
import it.unicam.cs.pa.jbudget102627.IdManagerInterface;
import it.unicam.cs.pa.jbudget102627.budget.Budget;
import it.unicam.cs.pa.jbudget102627.budget.BudgetInterface;
import it.unicam.cs.pa.jbudget102627.budget.BudgetManager;
import it.unicam.cs.pa.jbudget102627.ledge.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ha la responsabilita' di caricare i dati dai file txt nel programma.
 */
public class Loader implements LoadInterface{

    /**
     * Crea un nuovo controller e carica tutti gli elementi salvati su disco.
     * @param path Stringa del percorso ove sono salvati i file
     * @return controller con tutti gli oggetti caricati.
     * @throws IOException viene lanciato se ci sono problemi a leggere i file.
     */
    @Override
    public Controller loadController(String path) throws IOException {
        path = path.concat("/jbudget_saves");
        Controller c = new Controller(new Ledge(),new BudgetManager(), loadIDManager(path));
        loadAccounts(path,c);
        loadTags(path,c);
        loadMovements(path,c);
        loadTransactions(path,c);
        loadScheduleds(path,c);
        loadBudgets(path,c);
        return c;
    }

    @Override
    public boolean checkSave(String s) {
        return new File(s.concat("/jbudget_saves")).getAbsoluteFile().exists();
    }

    /**
     * Carica gli account e li "resetta" portando il bilancio a quello iniziale
     * e svuotando la lista dei movimenti.
     * @param s percorso della directory
     * @param c nuovo controller
     * @throws IOException se lo scanner non riesce a leggere il file
     */
    private void loadAccounts(String s,Controller c) throws IOException {
        File f = new File(s.concat("/accounts.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            AccountInterface acc = gson.fromJson(scanner.nextLine(), Account.class);
            acc.setMovements(new ArrayList<>());
            acc.balanceToOpening();
            c.addAccount(acc);
        }
    }

    private void loadTags(String s,Controller c) throws IOException {
        File f = new File(s.concat("/tags.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            TagInterface tag = gson.fromJson(scanner.nextLine(), Tag.class);
            c.addTag(tag);
        }
    }

    private void loadTransactions(String s,Controller c) throws IOException {
        File f = new File(s.concat("/transactions.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            TransactionInterface tra = gson.fromJson(scanner.nextLine(), Transaction.class);
            c.addTransaction(tra);
        }
    }

    private void loadScheduleds(String s, Controller c) throws IOException {
        File f = new File(s.concat("/scheduleds.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            TransactionInterface tra = gson.fromJson(scanner.nextLine(), Transaction.class);
            c.addTransaction(tra);
        }
    }

    private void loadBudgets(String s,Controller c) throws IOException {
        File f = new File(s.concat("/budgets.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            BudgetInterface bud = gson.fromJson(scanner.nextLine(), Budget.class);
            c.addBudget(bud);
        }
    }

    private void loadMovements(String s,Controller c) throws IOException {
        File f = new File(s.concat("/movements.txt"));
        if(!f.getAbsoluteFile().exists())
            return;
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            MovementInterface mov = gson.fromJson(scanner.nextLine(), Movement.class);
            c.addMovement(mov);
        }
    }

    private IdManagerInterface loadIDManager(String s) throws IOException {
        File f = new File(s.concat("/idmanager.txt"));
        if(!f.getAbsoluteFile().exists())
            return new IDManager();
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        return gson.fromJson(scanner.nextLine(), IDManager.class);
    }
}
