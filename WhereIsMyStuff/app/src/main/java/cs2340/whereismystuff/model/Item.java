package cs2340.whereismystuff.model;

class Item {
    private String _name;
    private ItemType _type;
    private String _description;
    private User _user;

    Item(String name, ItemType type, String description, User user) {
        _name = name;
        _type = type;
        _description = description;
        _user = user;
    }

    String getName() {
        return _name;
    }

    String getDescription() {
        return "Name: " + _name + "\nType: " + _type + "\nDescription: " +
                _description + "\nUser: " + _user.getName();
    }

    @Override
    public String toString() {
        return _type.toString() + ": " + _name;
    }
}
