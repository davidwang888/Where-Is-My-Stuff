package cs2340.whereismystuff.model;
import java.util.ArrayList;

/**
 * Created by carolinekish on 6/15/17.
 */

class ItemManager {
    private ArrayList<Item> _lostItems;

    private static final ItemManager instance = new ItemManager();

    private ItemManager() {
        _lostItems = new ArrayList<>();
    }

    static ItemManager getInstance() { return instance; }

    void addItem(Item item) {
        _lostItems.add(item);
    }

    void addItem(String name, ItemType type, String description, User user) {
        addItem(new Item(name, type, description, user));
    }

    ArrayList<Item> getLostItems() { return _lostItems; }
}

