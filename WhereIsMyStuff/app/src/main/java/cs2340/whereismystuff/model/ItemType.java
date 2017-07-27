package cs2340.whereismystuff.model;

/**
 * A representation of all the types of items
 */
public enum ItemType {

    /**
     * The different types an item can be
     */
    TECHNOLOGICAL ("technological"),
    FURNITURE ("furniture"),
    RECREATIONAL ("recreational"),
    PERSONAL ("personal"),
    PET ("pet"),
    OTHER ("other");

    /**
     * The string representation of the type
     */
    private final String _type;


    /**
     * Creates a new item type
     *
     * @param ptype the name of the item type
     */
    ItemType(String ptype) {
        _type = ptype;
    }

    @Override
    public String toString() {
        return _type;
    }
}
