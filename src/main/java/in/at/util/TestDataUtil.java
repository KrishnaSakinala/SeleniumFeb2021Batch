package in.at.util;

import java.util.Locale;

import com.github.javafaker.Faker;

public class TestDataUtil {
	
	static Faker faker = new Faker(new Locale("en-IND"));
	
	public static String firstName() {
        return faker.name().firstName();
    }

    public static String lastName() {
        return faker.name().lastName();
    }

    public static String username() {
        return faker.name().username();
    }

    public static String password() {
        return faker.internet().password();
    }
}