package entities;

import com.github.javafaker.Faker;
import utilities.Logs;

public class User {

    private final String fullName;
    private final int age;
    private final double balance;
    private final String username;
    private final String password;

    public User() {
        Logs.debug("Creating the user object");

        final var faker = new Faker();
        fullName = faker.name().fullName();
        age = faker.number().numberBetween(20, 40);
        balance = faker.number().randomDouble(2, 1000, 10000);
        username = faker.name().username();
        password = faker.internet().password();
    }

    @Override
    public String toString() {
        final var multiLine = """              
                \nUser
                {
                    fullName: %s
                    age: %d
                    balance: %.2f
                    username: %s
                    password: %s
                }
                """;
        return String.format(multiLine, fullName, age, balance, username, password);
    }
}
