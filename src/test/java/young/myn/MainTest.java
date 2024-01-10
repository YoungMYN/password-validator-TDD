package young.myn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class MainTest {
    @Test
    public void lengthTest(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            assertEquals(PasswordValidator.validatePassword(stringBuilder.toString()), PasswordStatus.INCORRECT);
            stringBuilder.append("#");
        }
        for (int i = 0; i < 15; i++) {
            assertNotEquals(PasswordValidator.validatePassword(stringBuilder.toString()), PasswordStatus.INCORRECT);
            stringBuilder.append("#");
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(PasswordValidator.validatePassword(stringBuilder.toString()), PasswordStatus.INCORRECT);
            stringBuilder.append("#");
        }
    }
    @Test
    public void latinAlphabetTest(){
        assertEquals(PasswordValidator.validatePassword("русскийязык$77"), PasswordStatus.INCORRECT);
        assertEquals(PasswordValidator.validatePassword("$7你好754你好"), PasswordStatus.INCORRECT);
        assertEquals(PasswordValidator.validatePassword("$7hellowörd"), PasswordStatus.INCORRECT);

        assertNotEquals(PasswordValidator.validatePassword("ABCfdsh3&dfjll"), PasswordStatus.INCORRECT);
        assertNotEquals(PasswordValidator.validatePassword("qwerty1??"), PasswordStatus.INCORRECT);
        assertNotEquals(PasswordValidator.validatePassword("dsfdddRReer^**274jj"), PasswordStatus.INCORRECT);
    }
    @Test
    public void specialCharacterTest(){
        assertEquals(PasswordValidator.validatePassword("helloworld"), PasswordStatus.INCORRECT);
        assertEquals(PasswordValidator.validatePassword("01122000"), PasswordStatus.INCORRECT);
        assertEquals(PasswordValidator.validatePassword("java8java"), PasswordStatus.INCORRECT);

        assertNotEquals(PasswordValidator.validatePassword("helloworld%"), PasswordStatus.INCORRECT);
        assertNotEquals(PasswordValidator.validatePassword("01122000&"), PasswordStatus.INCORRECT);
        assertNotEquals(PasswordValidator.validatePassword("java8java*"), PasswordStatus.INCORRECT);
    }
}