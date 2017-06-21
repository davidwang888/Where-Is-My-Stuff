package cs2340.whereismystuff.model;

/**
 * Represents a item and contains all of the information about that item
 */
class Item {
    /**
     * The item's name
     */
    private String _name;

    /**
     * The item's type
     */
    private ItemType _type;

    /**
     * The item's description
     */
    private String _description;

    /**
     * The user who inputted the item
     */
    private User _user;

    /**
     * Creates an item with the information passed in
     *
     * @param name the new item's name
     * @param type the new item's type
     * @param description the new item's description
     * @param user the new item's user
     */
    Item(String name, ItemType type, String description, User user) {
        _name = name;
        _type = type;
        _description = description;
        _user = user;
    }

    /**
     * Returns the item's name
     *
     * @return the item's name
     */
    String getName() {
        return _name;
    }

    /**
     * Returns a string with the item's description for the search screen
     *
     * @return a string of the item's description
     */
    String getDescription() {
        return "Name: " + _name + "\nType: " + _type + "\nDescription: " +
                _description + "\nUser: " + _user.getName();
    }

    @Override
    public String toString() {
        return _type.toString() + ": " + _name;
    }
}
