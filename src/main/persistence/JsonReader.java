package persistence;

import model.Favorites;
import model.NailDesign;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a reader that reads favorites from JSON data stored in a file.
 */
public class JsonReader {
    private String source;

    /**
     * EFFECTS: constructs reader to read from the given source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /**
     * EFFECTS: reads favorites from file and returns it;
     * throws IOException if an error occurs reading data from file
     */
    public Favorites read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavorites(jsonObject);
    }

    /**
     * EFFECTS: reads source file as a string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    /**
     * EFFECTS: parses favorites from JSON object and returns it
     */
    private Favorites parseFavorites(JSONObject jsonObject) {
        Favorites favorites = new Favorites();
        JSONArray jsonArray = jsonObject.getJSONArray("favorites");

        for (Object json : jsonArray) {
            JSONObject nextDesign = (JSONObject) json;
            addNailDesign(favorites, nextDesign);
        }
        return favorites;
    }

    /**
     * MODIFIES: favorites
     * EFFECTS: parses NailDesign from JSON object and adds it to favorites
     */
    private void addNailDesign(Favorites favorites, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray tagsArray = jsonObject.getJSONArray("tags");
        ArrayList<String> tags = new ArrayList<>();

        for (Object t : tagsArray) {
            tags.add((String) t);
        }

        String image = jsonObject.getString("image");
        NailDesign design = new NailDesign(name, tags, image);
        favorites.addDesign(design);
    }
}