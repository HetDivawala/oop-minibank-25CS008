# MiniBank - Basic Student Version (OOPJ Practicals 1-14)

This is a simple console-based Java banking project. It uses beginner-friendly classes and methods and avoids frameworks and complicated architecture.

## Requirements
- JDK 17 or newer
- Windows: open this folder in VS Code and run `build.bat`
- Linux/macOS: run `bash build.sh`

## Run
```bash
java -jar minibank.jar
```

## Main menu
1. Open Account
2. Deposit
3. Withdraw
4. Transfer
5. Statement
6. List Accounts
7. Transaction Report
8. Batch Deposit Demo
9. Exit

Account data is saved in `data/accounts.dat` when you exit. Transactions are appended to `data/transactions.txt`.

## Practical concepts included
- Practical 1: `BankInfo` record, `MenuOption` enum, Scanner, loop and switch expression.
- Practical 2: Customer and Account data, constructors, private fields and getters.
- Practical 3: toString, equals, hashCode, nested Address, clone and instanceof.
- Practical 4: regular-expression validation methods.
- Practical 5: abstract Account and Savings/Current/FixedDeposit subclasses, inheritance and polymorphism.
- Practical 6: Transactable, InterestBearing, WithdrawRule and Premium interfaces.
- Practical 7: custom annotations and reflection validator.
- Practical 8: checked custom exceptions, try/catch/finally and transfer.
- Practical 9: Runnable AccountWorker and synchronized account methods.
- Practical 10: fixed thread pool, wait/notify transaction buffer and ordered account locks.
- Practical 11: Serializable accounts, save/load, transaction log and report.
- Practical 12: ArrayList, HashMap, ConcurrentHashMap and sorting.
- Practical 13: generic Repository with @Id reflection.
- Practical 14: BankService, menu integration, persistence and runnable JAR.

This is a basic integrated learning version. Some practical concepts (such as race-condition demonstration, a separate deadlock demonstration, complete customer persistence, and JUnit test execution) should be demonstrated separately if your faculty asks for the exact lab experiment.
