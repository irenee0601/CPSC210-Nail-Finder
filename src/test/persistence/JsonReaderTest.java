package persistence;

import model.Favorites;
import model.NailDesign;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        assertThrows(IOException.class, reader::read);
    }

    @Test
    void testReaderGeneralFavorites() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralFavorites.json");
        try {
            Favorites fav = reader.read();
            ArrayList<NailDesign> list = fav.getDesigns();
            assertEquals(2, list.size());

            NailDesign d0 = list.get(0);
            assertEquals("Ruby Sparkle", d0.getName());
            assertTrue(d0.getTags().contains("red"));
            assertTrue(d0.getTags().contains("glitter"));
            assertEquals("img1", d0.getImage());

            NailDesign d1 = list.get(1);
            assertEquals("Ocean Blue", d1.getName());
            assertTrue(d1.getTags().contains("blue"));
            assertTrue(d1.getTags().contains("short"));
            assertEquals("img2", d1.getImage());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

