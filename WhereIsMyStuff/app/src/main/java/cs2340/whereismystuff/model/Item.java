package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Represents a item and contains all of the information about that item
 */
public class Item {

    public String get_name() {
        return _name;
    }

    public ItemType get_type() {
        return _type;
    }

    public String get_description() {
        return _description;
    }

    public User get_user() {
        return _user;
    }

    public LatLng get_latLng() {
        return _latLng;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_type(ItemType _type) {
        this._type = _type;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    public void set_latLng(LatLng _latLng) {
        this._latLng = _latLng;
    }

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

    private LatLng _latLng;

    /**
     * Creates an item with the information passed in
     *
     * @param name the new item's name
     * @param type the new item's type
     * @param description the new item's description
     * @param user the new item's user
     */
    Item(String name, ItemType type, String description, User user, LatLng
            latLng) {
        _name = name;
        _type = type;
        _description = description;
        _user = user;
        _latLng = latLng;
    }

    public Item() {
        this("a", ItemType.FURNITURE, "d", new User(), new LatLng(0, 0));
    }
    /**
     * Returns the item's name
     *
     * @return the item's name
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns a string with the item's description for the search screen
     *
     * @return a string of the item's description
     */
    public String getDescription() {
        return "Name: " + _name + "\nType: " + _type + "\nDescription: " +
                _description + "\nUser: " + _user.getName();
    }

    public LatLng getLatLng() {
        return _latLng;
    }
    @Override
    public String toString() {
        return _type.toString() + ": " + _name;
    }
}
