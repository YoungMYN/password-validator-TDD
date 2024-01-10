package young.myn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PasswordValidator {
    public static PasswordStatus validatePassword(String password){
        if(!passwordIsCorrect(password)) return PasswordStatus.INCORRECT;

        if(password.replaceAll("[^a-zA-Z]","").equals("")) return PasswordStatus.WEAK;
        if(password.length()==8 && numberOfOccurrencesOfTheMostCommonCharacterInString(password)>=3) return PasswordStatus.WEAK;
        if(password.replaceAll("\\D","").equals("")) return PasswordStatus.MEDIUM;
        if(password.length()<10) return PasswordStatus.MEDIUM;
        if(password.matches("^\\D*\\d$")) return PasswordStatus.MEDIUM;
        return PasswordStatus.STRONG;
    }

    private static boolean passwordIsCorrect(String password){
        if(!password.matches("^.{8,22}$")) return false;
        if(!password.replaceAll("[\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}\\d]","").matches("^[a-zA-Z]*$")) return false;
        if(password.replaceAll("[^\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}]","").equals("")) return false;
        return true;
    }

    private static int numberOfOccurrencesOfTheMostCommonCharacterInString(String s){
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(c);

            if (val != null) map.put(c, val + 1);
            else map.put(c, 1);
        }
        return Collections.max(map.values());
    }
}
