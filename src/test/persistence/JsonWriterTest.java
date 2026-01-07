package persistence;

import model.Favorites;
import model.NailDesign;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFavorites() {
        try {
            Favorites fav = new Favorites();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFavorites.json");
            writer.open();
            writer.write(fav);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFavorites.json");
            fav = reader.read();
            assertEquals(0, fav.getDesigns().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFavorites() {
        try {
            Favorites fav = createSampleFavorites();
            saveFavoritesToFile(fav);
            verifyFavoritesLoadedCorrectly();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private Favorites createSampleFavorites() {
        Favorites fav = new Favorites();

        ArrayList<String> tags1 = new ArrayList<>();
        tags1.add("red");
        tags1.add("glitter");

        ArrayList<String> tags2 = new ArrayList<>();
        tags2.add("blue");
        tags2.add("short");

        NailDesign d1 = new NailDesign("Ruby Sparkle", tags1, "img1");
        NailDesign d2 = new NailDesign("Ocean Blue", tags2, "img2");

        fav.addDesign(d1);
        fav.addDesign(d2);

        return fav;
    }

    private void saveFavoritesToFile(Favorites fav) throws IOException {
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralFavorites.json");
        writer.open();
        writer.write(fav);
        writer.close();
    }

    private void verifyFavoritesLoadedCorrectly() throws IOException {
        JsonReader reader = new JsonReader("./data/testWriterGeneralFavorites.json");
        Favorites fav = reader.read();

        ArrayList<NailDesign> list = fav.getDesigns();
        assertEquals(2, list.size());
        assertEquals("Ruby Sparkle", list.get(0).getName());
        assertEquals("Ocean Blue", list.get(1).getName());
    }

}
