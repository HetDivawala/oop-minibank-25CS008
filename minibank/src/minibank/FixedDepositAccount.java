package minibank;
public class FixedDepositAccount extends Account implements InterestBearing {
    private static final long serialVersionUID=1L;
    public FixedDepositAccount(String name,long balance){super(name,balance);}
    @Override public double interestRate(){return 7.0;}
}
