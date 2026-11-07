class PasswordChecker {

    public static boolean hasLength(String passwd) {
        return (passwd.length() >= 8);
    }

    public static boolean hasUpperCase(String passwd) {
        return passwd.matches(".*[A-Z].*");
    }

    public static boolean hasDigits(String passwd) {
        return passwd.matches(".*[0-9].*");
    }

    public static boolean hasSpecial(String passwd) {
        return passwd.matches(".*[!@#$%^&*()].*");
    }

    public static String getStrength(String passwd) {
        int count = 0;

        if (hasLength(passwd)) count++;
        if (hasUpperCase(passwd)) count++;
        if (hasDigits(passwd)) count++;
        if (hasSpecial(passwd)) count++;

        String strStrength = switch(count) {
            case 0 -> "Password Invalid";
            case 1 -> "Weak";
            case 2,3 -> "Medium";
            case 4 -> "Strong";
            default -> "Something went wrong";
        };

        return strStrength;
    }
}