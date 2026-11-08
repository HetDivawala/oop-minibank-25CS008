import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args)
    {
        String[] logins = {"Tisha", "Het", "Tisha", "Rajesh", "Het", "diya", "sonam", "Rajesh", "Tisha"};

        HashSet<String> unique = new HashSet<>(Arrays.asList(logins));
        LinkedHashSet<String> ordered = new LinkedHashSet<>(Arrays.asList(logins));

        System.out.println("Total logins: " + logins.length);
        System.out.println("Unique users: " + unique.size());

        System.out.print("First three distinct: ");
        int count = 0;

        for(String name : ordered){
            if(count == 3) break;
            System.out.print(name + " ");
            count++;
        }
        System.out.println();
    }
}