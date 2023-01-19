import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class OrderGenerator {
    private static final int PHONE_NUMBER_LENGTH = 9;
    private static final String START_NUMBERS_OF_PHONE = "89";
    private static final int MAX_VALUE_OF_RENT_DAY = 10;
    private static final int NUMBER_OF_CHARACTERS_IN_COMMENT = 10;


    private static String[] firstNamesArray = new String[] { "Алексей", "Петр", "Вячеслав", "Игорь", "Денис", "Владимир", "Олег" }; // массив компаний
    private static String[] lastNamesArray = new String[] { "Петров", "Сидоров", "Васильев", "Пухляков", "Колбаскин", "Ирискин"  };
    private static String[] addressesArray = new String[] { "Москва, Ленина 15", "Москва, Пушкина 12", "Москва, Кутузова 1", "Москва, Чапаева 77" };
    private static String[] metroStationsArray = new String[] { "Бульвар Рокоссовского", "Черкизовская", "Преображенская площадь", "Сокольники", "Красносельская" };
    private static String[] deliveryDates = new String[] { "09.02.2023", "09.02.2023", "10.02.2023", "10.02.2023"};

    public static Order getOrderWithoutColors(){
        Object[] generatedData = generateData();
        return new Order((String) generatedData[0], (String) generatedData[1], (String) generatedData[2], (String) generatedData[3], (String) generatedData[4], (Integer) generatedData[5], (String) generatedData[6],(String)generatedData[7]);
    }

    public static Order getOrderWithOneColor(){
        Object[] generatedData = generateData();
        return new Order((String) generatedData[0], (String) generatedData[1], (String) generatedData[2], (String) generatedData[3], (String) generatedData[4], (Integer) generatedData[5], (String) generatedData[6],(String)generatedData[7], new String[]{"GREY"});
    }

    public static Order getOrderWithBothColors(){
        Object[] generatedData = generateData();
        return new Order((String) generatedData[0], (String) generatedData[1], (String) generatedData[2], (String) generatedData[3], (String) generatedData[4], 6, (String) generatedData[6],(String)generatedData[7], new String[]{"GREY", "BLACK"});
    }

    private static Object[] generateData(){
        String generatedFirstName = returnElementOfArray(firstNamesArray);
        String generatedLastName = returnElementOfArray(lastNamesArray);
        String generatedAddress = returnElementOfArray(addressesArray);
        String generatedMetroStation = returnElementOfArray(metroStationsArray);
        String generatedPhone = generatePhone();
        int generatedRentTime = generateRentTime();
        String generatedDeliveryDate = returnElementOfArray(deliveryDates);
        String generatedComment = generateComment();
        return new Object[] {generatedFirstName, generatedLastName, generatedAddress,generatedMetroStation,generatedPhone,generatedRentTime,generatedDeliveryDate,generatedComment
            };
    }
    private static String returnElementOfArray(String[] array){
        int randomValue = new Random().nextInt(array.length);
        return array[randomValue];
    }
     private static int generateRentTime(){
         return new Random().nextInt(MAX_VALUE_OF_RENT_DAY);
     }
    private static String generatePhone(){
        String s = "123456789";
        StringBuffer phone = new StringBuffer();

        for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
            phone.append(s.charAt(new Random().nextInt(s.length())));
        }
        return START_NUMBERS_OF_PHONE + phone.toString();
    }
    private static String generateComment(){
       return RandomStringUtils.randomAlphabetic(NUMBER_OF_CHARACTERS_IN_COMMENT);
    }





}





