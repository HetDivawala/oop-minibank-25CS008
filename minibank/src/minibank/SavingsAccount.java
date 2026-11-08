package minibank;
public class SavingsAccount extends Account implements InterestBearing, Premium {
    private static final long serialVersionUID=1L;
    public SavingsAccount(String name,long balance){super(name,balance);}
    @Override public double interestRate(){return 4.0;}
}
