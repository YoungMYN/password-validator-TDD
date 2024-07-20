package young.myn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PasswordValidator {
	public static PasswordStatus validatePassword(String password) {
		if (!passwordIsCorrect(password))
			return PasswordStatus.INCORRECT;

		if (passwordInDangerousList(password))
			return PasswordStatus.WEAK;
		if (password.replaceAll("[^a-zA-Z]", "").equals(""))
			return PasswordStatus.WEAK;
		if (password.length() == 8 && numberOfOccurrencesOfTheMostCommonCharacterInString(password) >= 3)
			return PasswordStatus.WEAK;
		if (password.replaceAll("\\D", "").equals(""))
			return PasswordStatus.MEDIUM;
		if (password.length() < 10)
			return PasswordStatus.MEDIUM;
		if (password.matches("^\\D*\\d$"))
			return PasswordStatus.MEDIUM;
		return PasswordStatus.STRONG;
	}

	private static boolean passwordIsCorrect(String password) {
		if (password == null)
			throw new IllegalArgumentException("Password can't be null");
		if (!password.matches("^.{8,22}$"))
			return false;
		if (!password.replaceAll("[\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}\\d]", "").matches("^[a-zA-Z]*$"))
			return false;
		if (password.replaceAll("[^\\\\!@#$%^&*()—_+=;:,./?|`~\\[\\]{}]", "").equals(""))
			return false;
		return true;
	}

	private static int numberOfOccurrencesOfTheMostCommonCharacterInString(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
		}
		return Collections.max(map.values());
	}

	private static boolean passwordInDangerousList(String password) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("src/main/resources/dangerous_passwords.txt"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Can't find file dangerous_passwords.txt");
		}
		while (scanner.hasNext()) {
			String dangerousPassword = scanner.next();
			if (password.equals(dangerousPassword))
				return true;
		}
		return false;
	}
}
