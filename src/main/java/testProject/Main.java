package testProject;

import com.github.javafaker.Faker;
import entities.Joke;
import entities.Monster;
import entities.Product;
import entities.Sportsman;
import entities.User;
import utilities.ExcelReader;
import utilities.JsonReader;
import utilities.Logs;
import utilities.MapParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        executeRandomDataProgram();
        executeExcelReaderProgram();
        executeJsonReaderProgram();
    }

    private static List<User> createUsersList(int number) {
        Logs.info("Creating the list of users");
        final var usersList = new ArrayList<User>();
        for (var i = 0; i < number; i++) {
            // Create a random user
            usersList.add(new User());
        }
        return usersList;
    }

    private static List<Monster> getAirTypeList(List<Monster> totalList) {
        Logs.info("Creating the monsters list of type air");
        final var airList = new ArrayList<Monster>();

        for (var monster : totalList) {
            if (monster.getType().equals("AIRE")) {
                airList.add(monster);
            }
        }

        return airList;
    }

    private static double calculateVolume(Product[] array) {
        var sum = 0.0;
        for (var product : array) {
            sum += product.getVolume();
        }
        return sum;
    }

    private static void sortArray(Joke[] arrayJoke){
        final var n = arrayJoke.length;

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n - i - 1; j++) {
                if(arrayJoke[j].getId() > arrayJoke[j + 1].getId()) {
                    final var temp = arrayJoke[j];
                    arrayJoke[j] = arrayJoke[j + 1];
                    arrayJoke[j + 1] = temp;
                }
            }
        }
    }

    private static void executeRandomDataProgram() {
        // Sportsman exercise
        System.out.println("\n---SPORTSMAN EXERCISE---\n");
        Logs.info("Instantiating the sportsman 1");
        final var sportsMan1 = new Sportsman();

        Logs.info("Instantiating the sportsman 2");
        final var sportsMan2 = new Sportsman();

        final var multiLine = """
                sportsMan1 :
                %s
                                
                sportsMan2 :
                %s
                """;

        Logs.info("Printing the information of both sportsmen");
        System.out.printf(multiLine, sportsMan1, sportsMan2);

        Logs.info("Finding out the fastest sportsman");
        if(sportsMan1.getSpeed() > sportsMan2.getSpeed()) {
            System.out.printf("The fastest sportsman is: %s%n", sportsMan1.getFirstName());
        } else {
            System.out.printf("The fastest sportsman is: %s%n", sportsMan2.getFirstName());
        }

        // Users exercise
        System.out.println("\n---USERS EXERCISE---\n");
        final var faker = new Faker();

        Logs.info("Creating a random number between 20 and 50");
        final var randNumber = faker.number().numberBetween(20, 50);

        final var usersList = createUsersList(randNumber);

        Logs.info("Showing the list in the console");
        System.out.printf("the list is: %s%n", usersList);
    }

    private static void executeExcelReaderProgram() {
        // Monsters exercise
        System.out.println("\n---MONSTERS EXERCISE---\n");
        final var totalList = ExcelReader.getMonstersList();
        final var typeAirList = getAirTypeList(totalList);
        System.out.printf("The list is: %n%s%n", typeAirList);

        // Products exercise
        System.out.println("\n---PRODUCTS EXERCISE---\n");
        final var productMap = MapParser.getProductMap();
        final var productArray = new Product[3];

        productArray[0] = productMap.get("LECHE");
        productArray[1] = productMap.get("ARROZ");
        productArray[2] = productMap.get("AVENA");

        final var volume = calculateVolume(productArray);

        System.out.printf("The total volume is: %.2f%n", volume);
    }

    private static void executeJsonReaderProgram() {
        System.out.println("\n---USERS EXERCISE---\n");
        final var userJson = JsonReader.getUserJson("src/main/resources/data/user.json");

        final var name = userJson.getName();
        final var lat = userJson.getAddress().getGeo().getLat();
        final var lng = userJson.getAddress().getGeo().getLng();
        final var catchPhrase = userJson.getCompany().getCatchPhrase();

        final var multiLine = """
                {
                    name: %s,
                    lat: %.2f,
                    lng: %.2f,
                    catchPhrase: %s               
                }
                """;

        System.out.printf(multiLine, name, lat, lng, catchPhrase);

        System.out.println("\n---JOKES EXERCISE---\n");
        final var arrayJokes = JsonReader.getJokeArray("src/main/resources/data/jokes.json");
        sortArray(arrayJokes);

        System.out.printf("First: %s", arrayJokes[0]);
        System.out.printf("Second: %s", arrayJokes[1]);
        System.out.printf("Third: %s", arrayJokes[2]);
    }
}
