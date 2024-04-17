package entities;

import com.github.javafaker.Faker;
import utilities.Logs;

public class Sportsman {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final double speed;
    private final double stamina;
    private final double strength;
    private final String email;

    public Sportsman() {
        Logs.debug("Creating the sportsman object");

        final var faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        age = faker.number().numberBetween(20, 60);
        speed = faker.number().randomDouble(2, 20, 80);
        stamina = faker.number().randomDouble(2, 15, 50);
        strength = faker.number().randomDouble(2, 12, 60);
        email = faker.internet().emailAddress();
    }

    public String getFirstName() {
        return firstName;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        final var multiLine = """
                
                {
                    firstName: %s
                    lastName: %s
                    age: %d
                    speed: %.2f
                    stamina: %.2f
                    strength: %.2f
                    email: %s
                }
                """;
        return String.format(multiLine, firstName, lastName, age, speed, stamina, strength, email);
    }
}
