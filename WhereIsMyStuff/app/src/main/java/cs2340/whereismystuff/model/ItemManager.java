package cs2340.whereismystuff.model;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by carolinekish on 6/15/17.
 */

class ItemManager {
    private HashMap<String, Item> _lostItems;
    private HashMap<String, Item> _foundItems;

    private static final ItemManager instance = new ItemManager();

    private ItemManager() {
        _lostItems = new HashMap<>();
        _foundItems = new HashMap<>();
    }

    static ItemManager getInstance() { return instance; }

    void addLostItem(Item item) {
        _lostItems.put(item.getName(), item);
    }

    void addLostItem(String name, ItemType type, String description, User
            user) {
        addLostItem(new Item(name, type, description, user));
    }

    void addFoundItem(Item item) {
        _foundItems.put(item.getName(), item);
    }

    void addFoundItem(String name, ItemType type, String description, User
            user) {
        addFoundItem(new Item(name, type, description, user));
    }

    Item findLostItem(String name) {
        return _lostItems.get(name);
    }

    Item findFoundItem(String name) {
        return _foundItems.get(name);
    }

    ArrayList<Item> getLostItems() {
        return new ArrayList<>(_lostItems.values());
    }

    ArrayList<Item> getFoundItems() {
        return new ArrayList<>(_foundItems.values());
    }
}

