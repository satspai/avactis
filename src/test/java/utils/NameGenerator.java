package utils;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private static final Random random = new Random();
    private static final List<String> firstNames = new ArrayList<>();
    private static final List<String> lastNames = new ArrayList<>();
    private static final List<String> postcode = new ArrayList<>();
    private static final List<String> city = new ArrayList<>();

    static {
        // Initialize first names
        firstNames.add("Alice"); firstNames.add("Bob"); firstNames.add("Charlie");
        firstNames.add("David"); firstNames.add("Eve"); firstNames.add("Frank");
        firstNames.add("Grace"); firstNames.add("Henry"); firstNames.add("Ivy");
        firstNames.add("Jack");

        // Initialize last names
        lastNames.add("Smith"); lastNames.add("Johnson"); lastNames.add("Brown");
        lastNames.add("Taylor"); lastNames.add("Anderson"); lastNames.add("Wilson");
        lastNames.add("Davis"); lastNames.add("Clark"); lastNames.add("Lewis");
        lastNames.add("Walker");

        // Initialize postcodes
        postcode.add("1111"); postcode.add("2222"); postcode.add("3333");
        postcode.add("6666"); postcode.add("5555"); postcode.add("4444");
        postcode.add("7777"); postcode.add("8888"); postcode.add("9999");
        postcode.add("1212");

        // Initialize citiews
        city.add("city1"); city.add("city2"); city.add("city8");
        city.add("city3"); city.add("city7"); city.add("city9");
        city.add("city4"); city.add("city6"); city.add("city10");
        city.add("city5");
    }

    public static String getRandomFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public static String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public static String getRandomPostcode() { return postcode.get(random.nextInt(postcode.size()));}

    public static String getRandomCity() { return city.get(random.nextInt(city.size()));}

    public static String generateEmail(String firstName, String lastName) {
        String emailDomain = "@example.com";
        String emailUsername = (firstName + lastName).toLowerCase();
        return emailUsername + emailDomain;
    }

    public static String generateRandomEmail() {
        String firstName = getRandomFirstName();
        String lastName = getRandomLastName();
        return generateEmail(firstName, lastName);
    }


   
}