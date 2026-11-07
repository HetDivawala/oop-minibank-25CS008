class Point {

    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Point))
            return false;

        Point p = (Point) o;

        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}

public class DistinctPoints {

    public static void main(String[] args) {

        int MAX = 5;

        Point pts[] = new Point[MAX];
        Point unique[] = new Point[MAX];

        java.util.Scanner sc = new java.util.Scanner(System.in);

        for (int i = 0; i < MAX; i++) {

            System.out.println("Enter x: ");
            int a = sc.nextInt();

            System.out.println("Enter y: ");
            int b = sc.nextInt();

            pts[i] = new Point(a, b);
        }

        int count = 0;
        boolean found;

        for (int i = 0; i < MAX; i++) {

            found = false;

            for (int j = 0; j < count; j++) {

                if (pts[i].equals(unique[j])) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                unique[count] = pts[i];
                count++;
            }
        }

        System.out.println("Distinct: " + count);

        sc.close();
    }
}