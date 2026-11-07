class VendingMachine {
    public static void main(String[] args) {

        enum Coins { ONE, TWO, FIVE, TEN };

        final int PRICE = 15;
        int total = 0;
        Coins c = null;

        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (total < 15) {
            System.out.println("Taking input of coins");
            String str = sc.next();

            c = Coins.valueOf(str.toUpperCase());

            switch(c) {
                case ONE:
                    total += 1;
                    System.out.println("Paid: " + total);
                    break;

                case TWO:
                    total += 2;
                    System.out.println("Paid: " + total);
                    break;

                case FIVE:
                    total += 5;
                    System.out.println("Paid: " + total);
                    break;

                case TEN:
                    total += 10;
                    System.out.println("Paid: " + total);
                    break;

                default:
                    System.out.println("Invalid Value!\n");
            }
        }

        System.out.println("Paid: " + total + " Change: " + (total - PRICE));

        sc.close();
    }
}