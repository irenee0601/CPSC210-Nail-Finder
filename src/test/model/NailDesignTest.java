package model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class NailDesignTest {
    private NailDesign testDesign;
    private ArrayList<String> testTags;

    @BeforeEach
    void runBefore() {
        testTags = new ArrayList<>();
        testTags.add("red");
        testTags.add("glitter");
        testDesign = new NailDesign("Ruby Sparkle", testTags, "image path");
    }

    @Test
    void testConstructor() {
        assertEquals("Ruby Sparkle", testDesign.getName());
        assertEquals(testTags, testDesign.getTags());
        assertEquals("image path", testDesign.getImage());
    }

    @Test
    void testConstructorNullTags() {
        NailDesign nullDesign = new NailDesign("test", null, "image");
        assertNotNull(nullDesign.getTags());
        assertTrue(nullDesign.getTags().isEmpty());
    }

    @Test
    void testDeleteTagNullTag() {
        testDesign.deleteTag(null);
        assertTrue(testTags.contains("glitter"));
        assertTrue(testTags.contains("red"));
        assertEquals(2, testDesign.getTags().size());
    }

    @Test
    void testDeleteTagEmpty() {
        testDesign.deleteTag("");
        assertTrue(testTags.contains("glitter"));
        assertTrue(testTags.contains("red"));
        assertEquals(2, testDesign.getTags().size());
    }

    @Test
    void testDeleteTagNullTags() {
        NailDesign nullTags = new NailDesign("Name", null, "img");
        nullTags.deleteTag("red");
        assertNotNull(nullTags.getTags());
        assertTrue(nullTags.getTags().isEmpty());
    }

    @Test
    void testAddTag() {
        testDesign.addTag("black");
        assertTrue(testTags.contains("black"));
        assertTrue(testTags.contains("red"));
        assertTrue(testTags.contains("glitter"));
        assertEquals(3, testDesign.getTags().size());
    }

    @Test
    void testAddTagNullTag() {
        testDesign.addTag(null);
        assertTrue(testTags.contains("glitter"));
        assertTrue(testTags.contains("red"));
        assertEquals(2, testDesign.getTags().size());
    }

    @Test
    void testAddTagEmpty() {
        testDesign.addTag("");
        assertTrue(testTags.contains("glitter"));
        assertTrue(testTags.contains("red"));
        assertEquals(2, testDesign.getTags().size());
    }

    @Test
    void testAddTagNullTags() {
        NailDesign nullTags = new NailDesign("Name", null, "img");
        nullTags.addTag("red");
        assertNotNull(nullTags.getTags());
        assertFalse(nullTags.getTags().isEmpty());
    }

    @Test
    void testAddTagAlreadyExists() {
        testDesign.addTag("red");
        assertEquals(2, testDesign.getTags().size());
        assertTrue(testDesign.getTags().contains("red"));
    }

    @Test
    void testMatchesAllNullSearchTags() {
        assertFalse(testDesign.matchesAll(null));
    }

    @Test
    void testMatchesAllEmptySearchTags() {
        ArrayList<String> empty = new ArrayList<>();
        assertTrue(testDesign.matchesAll(empty));
    }

    @Test
    void testMatchesAllNoMatch() {
        ArrayList<String> searchTags = new ArrayList<>();
        searchTags.add("blue"); // testDesign에는 red, glitter만 있음
        assertFalse(testDesign.matchesAll(searchTags));
    }

    @Test
    void testMatchesAllAllMatch() {
        ArrayList<String> searchTags = new ArrayList<>();
        searchTags.add("red");
        searchTags.add("glitter");
        assertTrue(testDesign.matchesAll(searchTags));
    }

    @Test
    void testMatchesAll() {
        ArrayList<String> searchTags = new ArrayList<>();
        searchTags.add("red");

        ArrayList<String> searchTags2 = new ArrayList<>();
        searchTags2.add("blue");

        assertTrue(testDesign.matchesAll(searchTags));
        assertFalse(testDesign.matchesAll(searchTags2));
    }

}
