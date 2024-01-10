package young.myn;

public class PasswordValidator {
    public static PasswordStatus validatePassword(String password){
        if(!passwordIsCorrect(password)) return PasswordStatus.INCORRECT;
        return null;
    }
    private static boolean passwordIsCorrect(String password){
        if(!password.matches("^.{8,22}$")) return false;
        if(!password.replaceAll("[\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}\\d]","").matches("^[a-zA-Z]*$")) return false;
        if(password.replaceAll("[^\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}]","").equals("")) return false;
        return true;
    }
}
