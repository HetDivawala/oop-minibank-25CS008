abstract class Employee {

    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract double monthlySalary();

    public String getName() { return name; }
    public int getId() { return id; }
}

class FullTime extends Employee {
    private double fixedSalary;

    public FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    @Override
    public double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    private double hours;
    private double rate;

    public PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    private double stipend;

    public Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    public double monthlySalary() {
        return stipend;
    }
}

public class Payroll {

    public static void main(String[] args) {

        Employee[] employees = {
            new FullTime("Rajesh", 101, 5500.00),
            new PartTime("Het", 102, 80, 25.50),
            new Intern("Ram", 103, 1500.00),
            new FullTime("Mahesh", 104, 6200.00),
            new Intern("Sonam", 105, 1200.00)
        };

        double totalPayroll = 0.0;

        System.out.println("========== PAYROLL SUMMARY ==========");

        for (Employee emp : employees) {
            double salary = emp.monthlySalary();
            totalPayroll += salary;

            System.out.printf("ID: %d | Name: %-15s | Salary: $%,8.2f",
                    emp.getId(), emp.getName(), salary);

            if (emp instanceof Intern) {
                System.out.print(" [NOTE: Educational Stipend - Tax Exempt]");
            }

            System.out.println();
        }

        System.out.println("=====================================");
        System.out.printf("TOTAL PAYROLL AMOUNT: $%,.2f\n", totalPayroll);
    }
}