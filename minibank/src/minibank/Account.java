package minibank;
import java.io.Serializable;
import java.util.Objects;
public abstract class Account implements Transactable, Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;
    private static int counter=1000;
    @Id private final String accountNumber;
    private String ownerName;
    @Positive private long balance;
    private boolean active=true;
    protected Account(String ownerName, long openingBalance) {
        this.accountNumber="AC"+(++counter); this.ownerName=ownerName; this.balance=openingBalance;
    }
    public synchronized void deposit(long amount) throws BankException { if(amount<=0) throw new InvalidAmountException(); balance+=amount; }
    public synchronized void withdraw(long amount) throws BankException { if(amount<=0) throw new InvalidAmountException(); if(amount>balance) throw new InsufficientFundsException(amount-balance); balance-=amount; }
    public synchronized void transfer(Account to,long amount) throws BankException {
        try { withdraw(amount); to.deposit(amount); }
        catch(BankException e) { throw e; }
        finally { System.out.println("Transfer attempt completed."); }
    }
    public String getAccountNumber(){return accountNumber;} public String getOwnerName(){return ownerName;}
    public synchronized long getBalance(){return balance;} public boolean isActive(){return active;}
    public void closeAccount(){active=false;}
    public abstract double interestRate();
    @Override public int compareTo(Account other){return Long.compare(getBalance(),other.getBalance());}
    @Override public String toString(){return accountNumber+" | "+ownerName+" | Rs. "+getBalance()+" | Active: "+active;}
    @Override public boolean equals(Object obj){if(this==obj)return true; if(!(obj instanceof Account))return false; Account a=(Account)obj; return accountNumber.equals(a.accountNumber);}
    @Override public int hashCode(){return Objects.hash(accountNumber);}
}
