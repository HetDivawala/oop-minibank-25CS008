public class Shape
{
    public double getArea() { return -1; }
}

class Triangle extends Shape
{
    private double b;
    private double h;

    Triangle (int[] dimensions)
    {
        b = dimensions[0];
        h = dimensions[1];
    }

    @Override
    public double getArea() { return 0.5*b*h; }
}

class Rectangle extends Shape
{
    private double l;
    private double b;

    Rectangle(int[] dimensions)
    {
        l = dimensions[0];
        b = dimensions[1];
    }

    @Override
    public double getArea() { return 2*(l+b); }
}

class Circle extends Shape
{
    private double r;

    Circle(int[] dimensions)
    {
        r = dimensions[0];
    }

    @Override
    public double getArea() { return Math.PI*r*r; }
}

class Main
{
    public static int MAX = 4;

    public static void main(String[] args)
    {
        Shape[] shapes = new Shape[MAX];

        shapes[0] = new Triangle(new int[]{5, 10});
        shapes[1] = new Rectangle(new int[]{4, 6});
        shapes[2] = new Circle(new int[]{7});
        shapes[3] = new Triangle(new int[]{8, 12});

        System.out.println("--- Demonstrating Polymorphism ---");

        for (int i = 0; i < shapes.length; i++) {
            String shapeType = shapes[i].getClass().getSimpleName();
            double area = shapes[i].getArea();

            System.out.printf("Shape %d (%s) Area: %.2f\n", (i + 1), shapeType, area);
        }
    }
}