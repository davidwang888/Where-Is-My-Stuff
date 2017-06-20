package cs2340.whereismystuff.model;

enum ItemType {
    TECHNOLOGICAL ("Technological"),
    FURNITURE ("Furniture"),
    RECREATIONAL ("Recreational"),
    PERSONAL ("Personal"),
    PET ("Pet"),
    OTHER ("Other");

    private final String _type;

    ItemType(String ptype) {
        _type = ptype;
    }

    String getType() {
        return _type;
    }

    @Override
    public String toString() {
        return _type;
    }
}
