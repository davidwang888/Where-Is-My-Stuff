package cs2340.whereismystuff.model;
import java.util.ArrayList;
import java.util.HashMap;

class ItemManager {
    private HashMap<String, Item> _lostItems;
    private HashMap<String, Item> _foundItems;
    private static final ItemManager instance = new ItemManager();

    private ItemManager() {
        _lostItems = new HashMap<>();
        _foundItems = new HashMap<>();
    }

    static ItemManager getInstance() {
        return instance;
    }

    void addLostItem(String name, int typePosition, String description, User
            user) {
        ItemType type = ItemType.values()[typePosition];
        _lostItems.put(name, new Item(name, type, description, user));
    }

    void addFoundItem(String name, int typePosition, String description, User
            user) {
        ItemType type = ItemType.values()[typePosition];
        _foundItems.put(name, new Item(name, type, description, user));
    }

    ArrayList<Item> getLostItems() {
        return new ArrayList<>(_lostItems.values());
    }

    ArrayList<Item> getFoundItems() {
        return new ArrayList<>(_foundItems.values());
    }

    boolean searchFound(Boolean foundItem, String name) {
        if (foundItem) {
            return _foundItems.get(name) != null;
        } else {
            return _lostItems.get(name) != null;
        }
    }

    String searchResult(Boolean foundItem, String name) {
        if (foundItem) {
            return _foundItems.get(name).getDescription();
        } else {
            return _lostItems.get(name).getDescription();
        }
    }
}

