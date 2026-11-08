package minibank;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BankServiceTest {
    @Test void depositIncreasesBalance() throws Exception {
        Account a=new SavingsAccount("Test",1000);
        a.deposit(500);
        assertEquals(1500,a.getBalance());
    }
    @Test void largeWithdrawalThrowsException() {
        Account a=new SavingsAccount("Test",1000);
        assertThrows(InsufficientFundsException.class,()->a.withdraw(2000));
    }
    @Test void multipleDepositsKeepCorrectBalance() throws Exception {
        Account a=new SavingsAccount("Test",0);
        Thread t1=new Thread(new AccountWorker(a,1000,1));
        Thread t2=new Thread(new AccountWorker(a,1000,1));
        t1.start();t2.start();t1.join();t2.join();
        assertEquals(2000,a.getBalance());
    }
}
