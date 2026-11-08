package minibank;
public interface Transactable { void deposit(long amount) throws BankException; void withdraw(long amount) throws BankException; }
