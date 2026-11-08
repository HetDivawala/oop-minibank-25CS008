public class BoundedStats {

    static <N extends Number> double average(N[] values)
    {
        if(values.length == 0){
            return 0;
        }

        double sum = 0;
        for(N v : values){
            sum += v.doubleValue();
        }

        return sum / values.length;
    }

    public static void main(String[] args)
    {
        Integer[] a = {1, 2, 3, 4};
        Double[] b = {1.5, 2.5, 3.5};
        Long[] c = {10L, 20L, 30L};

        System.out.println("Integer avg: " + average(a));
        System.out.println("Double avg: " + average(b));
        System.out.println("Long avg: " + average(c));

        // String[] d = {"a", "b"};
        // average(d); // compile error: String does not extend Number
    }
}