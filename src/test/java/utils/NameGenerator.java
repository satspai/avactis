package utils;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private static final Random random = new Random();
    private static final List<String> firstNames = new ArrayList<>();
    private static final List<String> lastNames = new ArrayList<>();

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
    }

    public static String getRandomFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public static String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

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