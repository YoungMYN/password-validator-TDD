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

    @Test
    public void noLettersIsWeakPass(){
        assertEquals(PasswordValidator.validatePassword("1234567#"),PasswordStatus.WEAK);
        assertEquals(PasswordValidator.validatePassword("&&@#$&&&$@#"),PasswordStatus.WEAK);
        assertEquals(PasswordValidator.validatePassword("45%^^&32"),PasswordStatus.WEAK);

        assertNotEquals(PasswordValidator.validatePassword("4a5%^^&s32"),PasswordStatus.WEAK);
        assertNotEquals(PasswordValidator.validatePassword("as1dfs3%%d"),PasswordStatus.WEAK);
        assertNotEquals(PasswordValidator.validatePassword("12a34b56c7#"),PasswordStatus.WEAK);
    }

    @Test
    public void eightSymbolsAndMoreThanTwoRepeatIsWeakPass(){
        assertEquals(PasswordValidator.validatePassword("1234aaa#"),PasswordStatus.WEAK);
        assertEquals(PasswordValidator.validatePassword("abababa?"),PasswordStatus.WEAK);
        assertEquals(PasswordValidator.validatePassword("&77&&&ab"),PasswordStatus.WEAK);

        assertNotEquals(PasswordValidator.validatePassword("1234aba#"),PasswordStatus.WEAK);
        assertNotEquals(PasswordValidator.validatePassword("abaccbr?"),PasswordStatus.WEAK);
        assertNotEquals(PasswordValidator.validatePassword("&77d&&&ab"),PasswordStatus.WEAK);
    }
    @Test
    public void noDigitsIsMediumPass(){
        assertEquals(PasswordValidator.validatePassword("$abc&cbat#^"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("*rewuewwd$poe"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("%^prePTn%ewe"),PasswordStatus.MEDIUM);

        assertNotEquals(PasswordValidator.validatePassword("76abccbat#"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("*rewuewwd$87"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("%^prePTnyyyyy6rrr"),PasswordStatus.MEDIUM);
    }

    @Test
    public void lessThanTenSymbolsIsMediumPass(){
        assertEquals(PasswordValidator.validatePassword("abc39$hyy"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("*rew9ewd$"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("%^3rePTn"),PasswordStatus.MEDIUM);

        assertNotEquals(PasswordValidator.validatePassword("abc39$hyy15404"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("*rew9ewd$2812"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("%^3rePTn2008"),PasswordStatus.MEDIUM);
    }

    @Test
    public void onlyOneDigitInTheEndIsMediumPass(){
        assertEquals(PasswordValidator.validatePassword("abc$&$hyy4"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("*rew(ewd$2"),PasswordStatus.MEDIUM);
        assertEquals(PasswordValidator.validatePassword("%^otrepppPTn8"),PasswordStatus.MEDIUM);

        assertNotEquals(PasswordValidator.validatePassword("$$$java8_19"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("hell0w0rl%d"),PasswordStatus.MEDIUM);
        assertNotEquals(PasswordValidator.validatePassword("02082004btc##"),PasswordStatus.MEDIUM);
    }

    @Test
    public void strongPasswordsTest(){
        assertEquals(PasswordValidator.validatePassword("2023harl&&ff"),PasswordStatus.STRONG);
        assertEquals(PasswordValidator.validatePassword("*hrnwtr/GGb0da"),PasswordStatus.STRONG);
        assertEquals(PasswordValidator.validatePassword("#007skyfall2010#"),PasswordStatus.STRONG);
    }


}