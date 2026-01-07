package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// describes a user's favorite nail designs collection.
public class Favorites implements Writable {
    private ArrayList<NailDesign> designs;

    /**
     * MODIFIES: this
     * EFFECTS: constructs an empty favorites collection.
     */
    public Favorites() {
        designs = new ArrayList<>();
    }

    public ArrayList<NailDesign> getDesigns() {
        return designs;
    }

    /**
     * REQUIRES: design != null
     * MODIFIES: this
     * EFFECTS: adds the given design to favorites if not already present.
     */
    public void addDesign(NailDesign design) {
        if (design == null) {
            return;
        }
        if (!designs.contains(design)) {
            designs.add(design);
            EventLog.getInstance().logEvent(new Event("Design added to favorites: " + design.getName()));
        }
    }

    /**
     * REQUIRES: design != null
     * MODIFIES: this
     * EFFECTS: removes the given design from favorites if present.
     */
    public void deleteDesign(NailDesign design) {
        if (design == null) {
            return;
        }
        designs.remove(design);
        EventLog.getInstance().logEvent(new Event("Design removed from favorites: " + design.getName()));
    }

    /**
    * EFFECTS: returns this favorites collection as a JSON object
    */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (NailDesign d : designs) {
            jsonArray.put(d.toJson());
        }

        json.put("favorites", jsonArray);
        return json;    
    }

}
