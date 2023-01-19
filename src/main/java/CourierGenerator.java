import org.apache.commons.lang3.RandomStringUtils;
public class CourierGenerator {

    private static final int NUMBER_OF_CHARACTERS_IN_LOGIN = 10;
    private static final int NUMBER_OF_CHARACTERS_IN_PASSWORD = 9;
    private static final int NUMBER_OF_CHARACTERS_IN_COMMENT = 8;


    public static Courier getValidCourier(){
        return new Courier(generateLogin(),generatePassword(),generateFirstName());
    }

    public static String generateLogin(){
        return RandomStringUtils.randomAlphabetic(NUMBER_OF_CHARACTERS_IN_LOGIN);
    }
    public static String generatePassword(){
        return RandomStringUtils.randomAlphabetic(NUMBER_OF_CHARACTERS_IN_PASSWORD);
    }
    public static String generateFirstName(){
        return RandomStringUtils.randomAlphabetic(NUMBER_OF_CHARACTERS_IN_COMMENT);
    }

}
