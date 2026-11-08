package minibank;
import java.lang.reflect.Field;
import java.util.*;
public class Repository<T> {
    private HashMap<String,T> data=new HashMap<>();
    private String getId(T obj) {
        try { for(Field f:obj.getClass().getSuperclass().getDeclaredFields()) if(f.isAnnotationPresent(Id.class)){f.setAccessible(true);return String.valueOf(f.get(obj));}
            for(Field f:obj.getClass().getDeclaredFields()) if(f.isAnnotationPresent(Id.class)){f.setAccessible(true);return String.valueOf(f.get(obj));}
        } catch(Exception e) { throw new RuntimeException(e); }
        throw new RuntimeException("@Id field not found");
    }
    public void save(T obj){data.put(getId(obj),obj);} public T findById(String id){return data.get(id);}
    public ArrayList<T> findAll(){return new ArrayList<>(data.values());} public void delete(String id){data.remove(id);}
    public int count(){return data.size();}
}
