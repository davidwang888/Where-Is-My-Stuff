package cs2340.whereismystuff.model;

/**
 * Created by carolinekish on 6/15/17.
 */

public class Item {
    private String _name;
    private ItemType _type;
    private String _description;
    private User _user;
    private static int count;

    Item(String name, ItemType type, String description, User user) {
        _name = name;
        _type = type;
        _description = description;
        _user = user;
        count++;
    }

    @Override
    public String toString() {
        return _type.toString() + ": " + _name;
    }


}
