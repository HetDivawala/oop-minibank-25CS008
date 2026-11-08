package minibank;
import java.lang.reflect.Field;
import java.util.ArrayList;
public class AnnotationValidator {
    public static String[] validate(Object obj) {
        ArrayList<String> errors=new ArrayList<>();
        for(Field f:obj.getClass().getDeclaredFields()) {
            try { f.setAccessible(true); Object value=f.get(obj);
                if(f.isAnnotationPresent(Positive.class) && value instanceof Number && ((Number)value).longValue()<0) errors.add(f.getName()+": "+f.getAnnotation(Positive.class).message());
                if(f.isAnnotationPresent(MaxLength.class) && value instanceof String && ((String)value).length()>f.getAnnotation(MaxLength.class).value()) errors.add(f.getName()+": maximum length exceeded");
            } catch(Exception e) { errors.add("Could not validate "+f.getName()); }
        }
        return errors.toArray(new String[0]);
    }
}
