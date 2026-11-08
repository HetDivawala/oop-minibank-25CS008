package minibank;
public class CurrentAccount extends Account {
    private static final long serialVersionUID=1L;
    public CurrentAccount(String name,long balance){super(name,balance);}
    @Override public double interestRate(){return 0.0;}
}
