package model;

import java.util.ArrayList;
import java.util.Arrays;

// describes a catalog of all available nail designs in the app
public class Catalog {
    private ArrayList<NailDesign> allDesigns;

    /**
     * MODIFIES: this
     * EFFECTS: constructs a catalog pre-loaded with 30 sample nail designs
     */
    public Catalog() {
        allDesigns = new ArrayList<>();
        loadSampleDesigns();
    }

    public ArrayList<NailDesign> getAllDesigns() {
        return allDesigns;
    }

    /**
     * REQUIRES: design != null
     * MODIFIES: this
     * EFFECTS: adds the design to the catalog.
                Users can brows and search designs but cannot add new one.
     */
    void addDesign(NailDesign design) {
      //  if (design == null) {
      //      return;
      //  }
        allDesigns.add(design);
    }

    /**
     * REQUIRES: searchTags != null
     * EFFECTS: returns a list of designs that match all the given tags
     */
    public ArrayList<NailDesign> searchByTags(ArrayList<String> searchTags) { 
        ArrayList<NailDesign> matches = new ArrayList<>();
        if (searchTags == null || searchTags.isEmpty()) { //if there is no user input tag
            return matches;                               //return null(empty list)
        }

        for (NailDesign d : allDesigns) {
            if (d.matchesAll(searchTags)) {
                matches.add(d);
            }
        }
        return matches;
    }

    /**
     * MODIFIES: this
     * EFFECTS: loads 30 sample nail designs with various tags and colors
     */
    @SuppressWarnings("methodlength")
    private void loadSampleDesigns() {
        addDesign(new NailDesign(
                "img1", new ArrayList<>(Arrays.asList("white", "long", "round", "glitter")), "img1"));
        addDesign(new NailDesign(
                "img2", new ArrayList<>(Arrays.asList("white", "long", "round", "french")), "img2"));
        addDesign(new NailDesign(
                "img3", new ArrayList<>(Arrays.asList("white", "short", "square", "french")), "img3"));
        addDesign(new NailDesign(
                "img4", new ArrayList<>(Arrays.asList("black", "short", "square", "full-color")), "img4"));
        addDesign(new NailDesign(
                "img5", new ArrayList<>(Arrays.asList("black", "short", "suqare", "gem")), "img5"));
        addDesign(new NailDesign(
                "img6", new ArrayList<>(Arrays.asList("black", "short", "square", "french")), "img6"));
        addDesign(new NailDesign(
                "img7", new ArrayList<>(Arrays.asList("black", "long", "round", "french")), "img7"));
        addDesign(new NailDesign(
                "img8", new ArrayList<>(Arrays.asList("red", "short", "square", "full-color")), "img8"));
        addDesign(new NailDesign(
                "img9", new ArrayList<>(Arrays.asList("red", "short", "round", "glitter")), "img9"));
        addDesign(new NailDesign(
                "img10", new ArrayList<>(Arrays.asList("red", "long", "round", "glitter")), "img10"));
        addDesign(new NailDesign(
                "img11", new ArrayList<>(Arrays.asList("black", "long", "round", "gem")), "img11"));
        addDesign(new NailDesign(
                "img12", new ArrayList<>(Arrays.asList("blue", "long", "round", "full-color")), "img12"));
        addDesign(new NailDesign(
                "img13", new ArrayList<>(Arrays.asList("blue", "long", "round", "glitter")), "img13"));
        addDesign(new NailDesign(
                "img14", new ArrayList<>(Arrays.asList("blue", "long", "suqare", "gem")), "img14"));
        addDesign(new NailDesign(
                "img15", new ArrayList<>(Arrays.asList("pink", "long", "square", "glitter")), "img15"));
        addDesign(new NailDesign(
                "img16", new ArrayList<>(Arrays.asList("pink", "short", "square", "full-color")), "img16"));
        addDesign(new NailDesign(
                "img17", new ArrayList<>(Arrays.asList("pink", "long", "round", "gem")), "img17"));
        addDesign(new NailDesign(
                "img18", new ArrayList<>(Arrays.asList("pink", "long", "square", "gem")), "img18"));
        addDesign(new NailDesign(
                "img19", new ArrayList<>(Arrays.asList("pink", "short", "square", "french")), "img19"));
        addDesign(new NailDesign(
                "img20", new ArrayList<>(Arrays.asList("blue", "short", "square", "french")), "img20"));
        addDesign(new NailDesign(
                "img21", new ArrayList<>(Arrays.asList("pink", "long", "round", "full-color")), "img21"));
        addDesign(new NailDesign(
                "img22", new ArrayList<>(Arrays.asList("white", "long", "round", "full-color")), "img22"));
        addDesign(new NailDesign(
                "img23", new ArrayList<>(Arrays.asList("black", "short", "square", "glitter")), "img23"));
        addDesign(new NailDesign(
                "img24", new ArrayList<>(Arrays.asList("red", "long", "round", "french")), "img24"));
        addDesign(new NailDesign(
                "img25", new ArrayList<>(Arrays.asList("white", "short", "square", "glitter")), "img25"));
        addDesign(new NailDesign(
                "img26", new ArrayList<>(Arrays.asList("black", "long", "square", "gem")), "img26"));
        addDesign(new NailDesign(
                "img27", new ArrayList<>(Arrays.asList("red", "short", "square", "gem")), "img27"));
        addDesign(new NailDesign(
                "img28", new ArrayList<>(Arrays.asList("blue", "long", "square", "glitter")), "img28"));
        addDesign(new NailDesign(
                "img29", new ArrayList<>(Arrays.asList("white", "short", "square", "gem")), "img29"));
        addDesign(new NailDesign(
                "img30", new ArrayList<>(Arrays.asList("red", "long", "square", "gem")), "img30"));
        addDesign(new NailDesign(
                "img31", new ArrayList<>(Arrays.asList("blue", "short", "round", "gem")), "img31"));
    }
}
