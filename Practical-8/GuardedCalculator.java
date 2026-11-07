class DivideByZeroException extends Exception
{
    DivideByZeroException(String msg){ super(msg); }
}

public class GuardedCalculator {

    static double calculate(double a, double b, char op) throws DivideByZeroException
    {
        return switch(op){
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if(b == 0){
                    throw new DivideByZeroException("Can't divide by zero!");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }

    public static void main(String[] args){

        java.util.Scanner sc = new java.util.Scanner(System.in);
        boolean done = false;
        int attempt = 0;

        while(!done){
            attempt++;
            try{
                System.out.println("Enter number, operator, number: ");
                String[] parts = sc.nextLine().trim().split("\\s+");

                if(parts.length != 3){
                    throw new IllegalArgumentException("Enter exactly 3 values!");
                }

                double a = Double.parseDouble(parts[0]);
                char op = parts[1].charAt(0);
                double b = Double.parseDouble(parts[2]);

                System.out.println("Result: " + calculate(a, b, op));
                done = true;
            }
            catch(NumberFormatException e){
                System.out.println("Invalid number, try again!");
            }
            catch(DivideByZeroException e){
                System.out.println(e.getMessage());
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            finally{
                System.out.println("[log] attempt " + attempt + " finished");
            }
        }

        sc.close();
    }
}