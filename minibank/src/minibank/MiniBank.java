package minibank;
import java.util.Scanner;
public class MiniBank {
    record BankInfo(String name,String branch) { }
    enum MenuOption { OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, STATEMENT, LIST, REPORT, BATCH, EXIT }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); BankService bank=new BankService();
        BankInfo info=new BankInfo("MiniBank","Ankleshwar Branch");
        System.out.println("====================================");System.out.println(info.name()+" - "+info.branch());
        int choice=0;
        while(choice!=9) {
            System.out.println("\n1. Open Account\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Statement\n6. List Accounts\n7. Transaction Report\n8. Batch Deposit Demo\n9. Exit");
            System.out.print("Enter choice: ");
            try { choice=Integer.parseInt(sc.nextLine()); }
            catch(Exception e){System.out.println("Please enter a valid number.");continue;}
            MenuOption option=switch(choice){case 1->MenuOption.OPEN_ACCOUNT;case 2->MenuOption.DEPOSIT;case 3->MenuOption.WITHDRAW;case 4->MenuOption.TRANSFER;case 5->MenuOption.STATEMENT;case 6->MenuOption.LIST;case 7->MenuOption.REPORT;case 8->MenuOption.BATCH;case 9->MenuOption.EXIT;default->null;};
            if(option==null){System.out.println("Invalid choice.");continue;}
            try {
                switch(option) {
                    case OPEN_ACCOUNT -> {System.out.print("Customer name: ");String name=sc.nextLine();System.out.print("Opening balance: ");long amount=Long.parseLong(sc.nextLine());Account a=bank.openAccount(name,amount);System.out.println("Account created: "+a.getAccountNumber());}
                    case DEPOSIT -> {System.out.print("Account number: ");String id=sc.nextLine();System.out.print("Amount: ");long amount=Long.parseLong(sc.nextLine());bank.deposit(id,amount);System.out.println("Deposit successful.");}
                    case WITHDRAW -> {System.out.print("Account number: ");String id=sc.nextLine();System.out.print("Amount: ");long amount=Long.parseLong(sc.nextLine());bank.withdraw(id,amount);System.out.println("Withdrawal successful.");}
                    case TRANSFER -> {System.out.print("From account: ");String from=sc.nextLine();System.out.print("To account: ");String to=sc.nextLine();System.out.print("Amount: ");long amount=Long.parseLong(sc.nextLine());bank.transfer(from,to,amount);System.out.println("Transfer successful.");}
                    case STATEMENT -> {System.out.print("Account number: ");System.out.println(bank.statement(sc.nextLine()));}
                    case LIST -> {for(Account a:bank.listAccounts())System.out.println(a);}
                    case REPORT -> BankStorage.report();
                    case BATCH -> {System.out.print("Account number: ");bank.runBatch(sc.nextLine());System.out.println("Batch completed.");}
                    case EXIT -> {bank.save();System.out.println("Data saved. Thank you for using MiniBank!");}
                }
            } catch(NumberFormatException e){System.out.println("Please enter a valid amount.");}
              catch(BankException e){System.out.println("Operation failed: "+e.getMessage());}
              finally { if(option!=MenuOption.EXIT)System.out.println("Operation checked."); }
        }
        sc.close();
    }
}
