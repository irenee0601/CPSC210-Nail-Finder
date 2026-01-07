package model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class CatalogTest {
    private Catalog catalog;
    private NailDesign design;
    private ArrayList<String> tags;

    @BeforeEach
    void runBefore() {
        tags = new ArrayList<>();
        tags.add("red");
        tags.add("glitter");

        design = new NailDesign("Ruby Sparkle", tags, "image1");

        catalog = new Catalog();
    }

    @Test
    void testConstructor() {
        assertNotNull(catalog.getAllDesigns());
        assertEquals(31, catalog.getAllDesigns().size());
    }

    @Test
    void testAddDesign() {
        catalog.addDesign(design);
        assertTrue(catalog.getAllDesigns().contains(design));
    }

    @Test
    void testSearchByTagsMatch() {
        catalog.addDesign(design);
        ArrayList<String> searchTags = new ArrayList<>();
        searchTags.add("red");
        searchTags.add("glitter");

        ArrayList<NailDesign> results = catalog.searchByTags(searchTags);
        assertTrue(results.contains(design));   
    }

    @Test
    void testSearchByTagsNull() {
        ArrayList<String> searchTags = null;
        catalog.addDesign(design);
        ArrayList<NailDesign> results = catalog.searchByTags(searchTags);
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchByTagsEmpty() {
        ArrayList<String> searchTags = new ArrayList<>();
        catalog.addDesign(design);

        ArrayList<NailDesign> results = catalog.searchByTags(searchTags);
        assertTrue(results.isEmpty());
    }


    @Test
    void testSearchByTagsNoMatch() {
        ArrayList<String> searchTags = new ArrayList<>();
        catalog.addDesign(design);
        searchTags.add("noMatchTag");

        ArrayList<NailDesign> results = catalog.searchByTags(searchTags);
        assertTrue(results.isEmpty());
        
    }
    
    
}