class Box<T>
{
    private T value;

    Box(T value){ this.value = value; }

    T get(){ return value; }
    void set(T value){ this.value = value; }
}

class Pair<A, B>
{
    private A first;
    private B second;

    Pair(A first, B second){
        this.first = first;
        this.second = second;
    }

    A getFirst(){ return first; }
    B getSecond(){ return second; }

    @Override
    public String toString(){ return "(" + first + ", " + second + ")"; }
}

public class GenericContainers {

    static <T> T first(T[] arr)
    {
        return arr[0];
    }

    public static void main(String[] args)
    {
        Box<String> sb = new Box<>("Hello");
        Box<Integer> ib = new Box<>(42);

        String s = sb.get();     // no cast needed
        int i = ib.get();
        // sb.set(10);       // compile error -> type safety

        System.out.println(s + " " + i);

        Pair<String, Integer> p = new Pair<>("Riya", 20);
        Pair<Double, Boolean> q = new Pair<>(3.14, true);

        System.out.println(p + " " + q);

        System.out.println(first(new String[]{"a", "b", "c"}));
        System.out.println(first(new Integer[]{10, 20, 30}));
    }
}