import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// model
record Task(String title, boolean done){}

// service
class TaskService
{
    private ArrayList<Task> tasks = new ArrayList<>();

    void add(String title)
    {
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be blank!");
        }
        tasks.add(new Task(title.trim(), false));
    }

    void complete(int index)
    {
        if(index < 0 || index >= tasks.size()){
            throw new IndexOutOfBoundsException("No task at " + index);
        }
        tasks.set(index, new Task(tasks.get(index).title(), true));
    }

    int count(){ return tasks.size(); }

    ArrayList<Task> getAll(){ return tasks; }
}

// menu (main)
public class AssembleAndTest {
    public static void main(String[] args)
    {
        TaskService service = new TaskService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n1. Add task\n2. Complete task\n3. List tasks\n4. Exit");
            String choice = sc.nextLine().trim();

            try{
                switch(choice){
                    case "1" -> {
                        System.out.println("Title: ");
                        service.add(sc.nextLine());
                    }
                    case "2" -> {
                        System.out.println("Task number: ");
                        service.complete(Integer.parseInt(sc.nextLine().trim()) - 1);
                    }
                    case "3" -> {
                        for(int i=0; i<service.count(); i++){
                            Task t = service.getAll().get(i);
                            System.out.println((i+1) + ". [" + (t.done() ? "x" : " ") + "] " + t.title());
                        }
                    }
                    case "4" -> running = false;
                    default -> System.out.println("Invalid option!");
                }
            }
            catch(IllegalArgumentException | IndexOutOfBoundsException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}

// JUnit 5 tests
class AssembleAndTestTests
{
    @Test
    void addingIncreasesCount()
    {
        TaskService s = new TaskService();
        s.add("Write report");
        assertEquals(1, s.count());
    }

    @Test
    void blankTitleThrows()
    {
        TaskService s = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> s.add("   "));
    }

    @Test
    void completeMarksTaskDone()
    {
        TaskService s = new TaskService();
        s.add("Study OOP");
        s.complete(0);
        assertTrue(s.getAll().get(0).done());
    }
}