package minibank;
public class InsufficientFundsException extends BankException {
    private long shortfall;
    public InsufficientFundsException(long shortfall) { super("Insufficient balance. Short by Rs. " + shortfall); this.shortfall = shortfall; }
    public long getShortfall() { return shortfall; }
}
