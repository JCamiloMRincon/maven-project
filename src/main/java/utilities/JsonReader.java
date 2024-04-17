package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import entities.Joke;
import entities.UserJson;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static UserJson getUserJson(String path) {
        Logs.debug("Reading the user");
        final var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(path), UserJson.class);
        } catch (IOException ioException) {
            Logs.error("ioException: %s", ioException.getLocalizedMessage());
            // The app fails because the JSON could not be read
            throw new RuntimeException();
        }

    }

    public static Joke[] getJokeArray(String path) {
        Logs.debug("Reading the array of jokes...");
        final var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(path), TypeFactory.defaultInstance().constructArrayType(Joke.class));
        } catch (IOException ioException) {
            Logs.error("ioException: %s", ioException.getLocalizedMessage());
            // The app fails because the JSON could not be read
            throw new RuntimeException();
        }
    }
}
