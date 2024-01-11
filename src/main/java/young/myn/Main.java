package young.myn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Welcome to Password Validator! Please create a password.
                It must comply with the rules below:
                1) Must not be shorter than 8 or longer than 22 characters
                2) Contains letters exclusively of the Latin alphabet (if it contains them at all)
                3) Must contain at least 1 special character
                Special characters     ! @ # $ % ^ & * ( ) — _ + = ; : , ./ ? \\ | ` ~ [ ] { }""");

        Scanner scanner = new Scanner(System.in);
        PasswordStatus status = PasswordValidator.validatePassword(scanner.next());
        String outputMessage = null;
        switch (status){
            case INCORRECT -> outputMessage = "Your password is incorrect. Please try another one.";
            case WEAK -> outputMessage ="Your password is weak. We recommend strengthening it to avoid hacking.";
            case MEDIUM -> outputMessage = "Your password has a medium security level.";
            case STRONG -> outputMessage = "Your password is very strong. Great!";
        }
        System.out.println(outputMessage);
    }
}