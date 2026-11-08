package minibank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
public class BankService {
    private ArrayList<Account> accounts;
    private Repository<Account> repository=new Repository<>();
    private ConcurrentHashMap<String,Account> safeAccounts=new ConcurrentHashMap<>();
    public BankService(){accounts=BankStorage.load();for(Account a:accounts){repository.save(a);safeAccounts.put(a.getAccountNumber(),a);}}
    public Account openAccount(String name,long amount) throws BankException {
        if(name==null || name.trim().isEmpty())throw new BankException("Name cannot be empty.");
        if(amount<0)throw new InvalidAmountException();
        Account a=new SavingsAccount(name,amount);accounts.add(a);repository.save(a);safeAccounts.put(a.getAccountNumber(),a);
        BankStorage.log("OPEN "+a.getAccountNumber()+" "+amount);return a;
    }
    public Account find(String id) throws AccountNotFoundException {Account a=repository.findById(id);if(a==null)throw new AccountNotFoundException();return a;}
    public void deposit(String id,long amount) throws BankException {Account a=find(id);a.deposit(amount);BankStorage.log("DEPOSIT "+id+" "+amount);}
    public void withdraw(String id,long amount) throws BankException {Account a=find(id);a.withdraw(amount);BankStorage.log("WITHDRAW "+id+" "+amount);}
    public void transfer(String from,String to,long amount) throws BankException {
        Account a=find(from),b=find(to); Account first=a.getAccountNumber().compareTo(b.getAccountNumber())<0?a:b; Account second=first==a?b:a;
        synchronized(first){synchronized(second){a.transfer(b,amount);}}
        BankStorage.log("TRANSFER "+from+" "+to+" "+amount);
    }
    public ArrayList<Account> listAccounts(){ArrayList<Account> list=repository.findAll();Collections.sort(list);return list;}
    public String statement(String id) throws BankException {return find(id).toString();}
    public void save(){BankStorage.save(accounts);}
    public void runBatch(String id) throws BankException {
        Account a=find(id);TransactionProcessor processor=new TransactionProcessor();
        for(int i=0;i<10;i++)processor.submit(new AccountWorker(a,100,1));processor.stop();
        BankStorage.log("BATCH DEPOSIT "+id+" 1000");
    }
}
