import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength { int value(); }

class SignupForm {
    @NotBlank @MaxLength(8)
    String username;

    @NotBlank
    String email;

    @MaxLength(5)
    String pincode;

    SignupForm(String username, String email, String pincode){
        this.username = username;
        this.email = email;
        this.pincode = pincode;
    }
}

public class FormValidator {

    static java.util.ArrayList<String> check(Object obj) throws IllegalAccessException
    {
        java.util.ArrayList<String> errors = new java.util.ArrayList<>();

        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            Object value = f.get(obj);

            if(f.isAnnotationPresent(NotBlank.class)){
                if(value == null || value.toString().trim().isEmpty()){
                    errors.add(f.getName() + " must not be blank");
                }
            }

            if(f.isAnnotationPresent(MaxLength.class) && value != null){
                int max = f.getAnnotation(MaxLength.class).value();
                if(value.toString().length() > max){
                    errors.add(f.getName() + " must be at most " + max + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args) throws IllegalAccessException
    {
        SignupForm good = new SignupForm("riya", "riya@xyz.com", "38001");
        SignupForm bad = new SignupForm(" ", "", "3800012");

        System.out.println("Good form errors: " + check(good));
        System.out.println("Bad form errors: " + check(bad));
    }
}