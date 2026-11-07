import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class MathTests {
    @Run
    public void testAddition(){
        if(2 + 3 != 5) throw new RuntimeException("2 + 3 should be 5");
    }

    @Run
    public void testStringLength(){
        if("java".length() != 4) throw new RuntimeException("length should be 4");
    }

    @Run
    public void testFailing(){
        throw new RuntimeException("this test fails on purpose");
    }

    public void helperNotATest(){
        System.out.println("This should never run!");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception
    {
        Class<?> c = MathTests.class;
        Object obj = c.getDeclaredConstructor().newInstance();

        Method[] methods = c.getDeclaredMethods();
        java.util.Arrays.sort(methods, java.util.Comparator.comparing(Method::getName));

        int ran = 0, passed = 0;

        for(Method m : methods){
            if(m.isAnnotationPresent(Run.class)){
                ran++;
                try{
                    m.invoke(obj);
                    passed++;
                    System.out.println("PASS: " + m.getName());
                }
                catch(java.lang.reflect.InvocationTargetException e){
                    System.out.println("FAIL: " + m.getName() + " -> " + e.getCause().getMessage());
                }
            }
        }

        System.out.println("Ran: " + ran + " Passed: " + passed + " Failed: " + (ran-passed));
    }
}