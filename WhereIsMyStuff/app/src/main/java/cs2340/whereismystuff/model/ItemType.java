package cs2340.whereismystuff.model;

/**
 * Created by carolinekish on 6/15/17.
 */

public enum ItemType {
    TECHNOLOGICAL ("Technological"),
    FURNITURE ("Furniture"),
    RECREATIONAL ("Recreational"),
    PERSONAL ("Personal"),
    PET ("Pet"),
    OTHER ("Other");

    private final String type;

    ItemType(String ptype) {
        type = ptype;
    }

    public String getType() {return type; }

    public String toString() { return type; }
}
