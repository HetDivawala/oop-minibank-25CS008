package minibank;
public class InvalidAmountException extends BankException {
    public InvalidAmountException() { super("Amount must be greater than zero."); }
}
