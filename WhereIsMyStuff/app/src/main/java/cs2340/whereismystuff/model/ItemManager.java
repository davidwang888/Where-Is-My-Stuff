package cs2340.whereismystuff.model;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reprsents a item manager that stores all the lost and found items and is
 * used to search for items
 */
class ItemManager {
    /**
     * A hashmap of all the lost items
     */
    private HashMap<String, Item> _lostItems;

    /**
     * A hashmap of all the found items
     */
    private HashMap<String, Item> _foundItems;


    /**
     * The singular instance of UserManager that will be used by the Model to
     * manage the users
     */
    private static final ItemManager instance = new ItemManager();

    /**
     * Creates a ItemManager
     */
    private ItemManager() {
        _lostItems = new HashMap<>();
        _foundItems = new HashMap<>();
    }

    /**
     * Returns the single instance of item manager
     * @return The item manager
     */
    static ItemManager getInstance() {
        return instance;
    }

    /**
     * Adds a lost item to the lost item collection
     *
     * @param name the name of the new item
     * @param typePosition the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    void addLostItem(String name, int typePosition, String description, User
            user) {
        ItemType type = ItemType.values()[typePosition];
        //start db stuff
        ContentValues values = new ContentValues();

        //end db stuff
        _lostItems.put(name, new Item(name, type, description, user));
    }

    /**
     * Adds a found item to the lost item collection
     *
     * @param name the name of the new item
     * @param typePosition the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    void addFoundItem(String name, int typePosition, String description, User
            user) {
        ItemType type = ItemType.values()[typePosition];
        _foundItems.put(name, new Item(name, type, description, user));
    }

    /**
     * Returns a list of all the lost items for the list view
     *
     * @return a list of all the lost items
     */
    ArrayList<Item> getLostItems() {
        return new ArrayList<>(_lostItems.values());
    }

    /**
     * Returns a list of all the found items for the list view
     *
     * @return a list of all the found items
     */
    ArrayList<Item> getFoundItems() {
        return new ArrayList<>(_foundItems.values());
    }

    /**
     * Looks for an item and returns whether the item is stored in the item
     * manager
     *
     * @param foundItem whether the item is a found item or not
     * @param name the name of the item
     * @return a boolean whether the item is found
     */
    boolean searchFound(Boolean foundItem, String name) {
        if (foundItem) {
            return _foundItems.get(name) != null;
        } else {
            return _lostItems.get(name) != null;
        }
    }

    /**
     * Looks for an item and returns the item from the item manager
     *
     * @param foundItem whether the item is a found item or not
     * @param name the name of the item
     * @return a boolena whether the item is found
     */
    String searchResult(Boolean foundItem, String name) {
        if (foundItem) {
            return _foundItems.get(name).getDescription();
        } else {
            return _lostItems.get(name).getDescription();
        }
    }
}

