package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class FavoritesTest {
    private Favorites favorites;
    private NailDesign design1;
    private NailDesign design2;
    private ArrayList<String> tags1;
    private ArrayList<String> tags2;

    @BeforeEach
    void runBefore() {
        tags1 = new ArrayList<>();
        tags1.add("red");
        tags1.add("short");

        tags2 = new ArrayList<>();
        tags2.add("blue");
        tags2.add("glitter");

        design1 = new NailDesign("Ruby Red", tags1, "image1");
        design2 = new NailDesign("Ocean Blue", tags2, "image2");

        favorites = new Favorites();
    }

    @Test
    void testConstructor() {
        assertNotNull(favorites.getDesigns());
        assertEquals(0, favorites.getDesigns().size());
    }

    @Test
    void testAddDesign() {
        favorites.addDesign(design1);
        assertTrue(favorites.getDesigns().contains(design1));
        favorites.addDesign(design1);
        assertTrue(favorites.getDesigns().contains(design1));
        assertEquals(1,favorites.getDesigns().size());
    }

    @Test
    void testAddDesignNull() { 
        favorites.addDesign(null);
        assertTrue(favorites.getDesigns().isEmpty());

    }

    @Test
    void testDeleteDesign() {
        favorites.addDesign(design1);
        favorites.addDesign(design2);
        favorites.deleteDesign(design1);
        assertFalse(favorites.getDesigns().contains(design1));
        assertTrue(favorites.getDesigns().contains(design2));
    }

    @Test
    void testDeleteDesignNull() {
        favorites.addDesign(design1);
        favorites.deleteDesign(null);
        assertTrue(favorites.getDesigns().contains(design1));
        assertEquals(1, favorites.getDesigns().size());
    }
}
