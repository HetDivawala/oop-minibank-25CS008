package minibank;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
public class BankStorage {
    private static final String FILE="data/accounts.dat";
    public static void save(ArrayList<Account> accounts) {
        try { Files.createDirectories(Paths.get("data")); ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(FILE)); out.writeObject(accounts); out.close(); }
        catch(Exception e){System.out.println("Could not save data: "+e.getMessage());}
    }
    @SuppressWarnings("unchecked") public static ArrayList<Account> load() {
        try { ObjectInputStream in=new ObjectInputStream(new FileInputStream(FILE)); ArrayList<Account> a=(ArrayList<Account>)in.readObject(); in.close(); return a; }
        catch(Exception e){return new ArrayList<>();}
    }
    public static void log(String message) {
        try { Files.createDirectories(Paths.get("data")); Files.writeString(Paths.get("data/transactions.txt"),message+System.lineSeparator(),StandardOpenOption.CREATE,StandardOpenOption.APPEND); }
        catch(Exception e){System.out.println("Log error: "+e.getMessage());}
    }
    public static void report() {
        try { Path p=Paths.get("data/transactions.txt"); if(Files.exists(p)){long lines=Files.lines(p).count(); System.out.println("Total transactions: "+lines); System.out.println("Log size: "+Files.size(p)+" bytes");} else System.out.println("No transactions yet."); }
        catch(Exception e){System.out.println(e.getMessage());}
    }
}
