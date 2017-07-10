package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Represents a item and contains all of the information about that item
 */
public class Item {
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
     * The location of the item
     */
    private LatLng _latLng;

    /**
     * Creates an item with the information passed in
     *
     * @param name the new item's name
     * @param type the new item's type
     * @param description the new item's description
     * @param user the new item's user
     * @param latLng the new item's location
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
     * Returns the item's type
     *
     * @return the item's type
     */
    public ItemType getType() {
        return _type;
    }

    /**
     * Returns the item's description
     *
     * @return the item's user
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Returns the item's user
     *
     * @return the item's user
     */
    public User getUser() {
        return _user;
    }

    /**
     * Returns the item's location
     *
     * @return the item's location
     */
    public LatLng getLatLng() {
        return _latLng;
    }

    /**
     * Sets the item's name
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Sets the item's type
     */
    public void setType(ItemType _type) {
        this._type = _type;
    }

    /**
     * Sets the item's description
     */
    public void setDescription(String _description) {
        this._description = _description;
    }

    /**
     * Sets the item's user
     */
    public void setUser(User _user) {
        this._user = _user;
    }

    /**
     * Sets the item's location
     */
    public void setLatLng(LatLng _latLng) {
        this._latLng = _latLng;
    }

    /**
     * Returns a string with the item's description for the search screen
     *
     * @return a string of the item's description
     */
    public String getSearchDescription() {
        return "Name: " + _name + "\nType: " + _type + "\nDescription: " +
                _description + "\nUser: " + _user.getName();
    }

    @Override
    public String toString() {
        return _type.toString() + ": " + _name;
    }
}
