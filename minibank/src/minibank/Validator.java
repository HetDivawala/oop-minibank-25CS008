package minibank;
public class Validator {
    public static boolean validEmail(String email){return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");}
    public static boolean validMobile(String mobile){return mobile.matches("[0-9]{10}");}
    public static boolean validPan(String pan){return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");}
    public static boolean validIfsc(String ifsc){return ifsc.matches("[A-Z]{4}0[A-Z0-9]{6}");}
}
