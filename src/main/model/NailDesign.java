package model;

import java.util.ArrayList;
import org.json.JSONObject;


// describes one nail design with a name, a list of tags, and an image path 
public class NailDesign {
    private String name;
    private ArrayList<String> tags;
    private String image;


    /**
     * REQUIRES: tags != null
     * MODIFIES: this
     * EFFECTS: contructs a design with given name, an tag list and an image
     */
    public NailDesign(String name, ArrayList<String> tags, String image) {
        this.name = name;
        if (tags == null) {
            this.tags = new ArrayList<>();
        } else {
            this.tags = tags;   //stores the given tag path in field
        }
        this.image = image;   //stores the given image path in field
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    /**
     * REQUIRES: tag != null && !tag.isEmpty()
     * MODIFIES: this
     * EFFECTS: removes the tag if present
     */
    public void deleteTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return;
        }
        tags.remove(tag);
    }

    /**
     * REQUIRES: tag != null && !tag.isEmpty()
     * MODIFIES: this
     * EFFECTS: adds the tag if not already present
     */
    public void addTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            return;
        }
        if (!tags.contains(tag)) {
            tags.add(tag);            
        }
    }
    
    /**
     * REQUIRES: searchTags != null
     * EFFECTS: return true if this design contains all tags in searchTags
     */
    public boolean matchesAll(ArrayList<String> searchTags) {
        if (searchTags == null) {
            return false;
        }
        if (searchTags.isEmpty()) {  //if search tag list is empty(user entered nothing)
            return true;
        }
       
        for (String s : searchTags) {
            if (!tags.contains(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * EFFECTS: returns this nail design as a JSON object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tags", tags);
        json.put("image", image);
        return json;
    }


}