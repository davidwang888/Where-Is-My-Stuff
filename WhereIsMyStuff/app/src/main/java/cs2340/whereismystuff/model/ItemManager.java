package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * The database reference to the whole Firebase database
     */
    private DatabaseReference _databaseRef;

    /**
     * The database reference to the lost item section of the Firebase database
     */
    private DatabaseReference _lostItemsDatabase;

    /**
     * The database reference to the found item section of the Firebase database
     */
    private DatabaseReference _foundItemsDatabase;

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
        _databaseRef = FirebaseDatabase.getInstance().getReference();
        _lostItemsDatabase = _databaseRef.child("lost items");
        _foundItemsDatabase = _databaseRef.child("found items");
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
    private void setUpAddLostItem(String name, int typePosition, String
            description, User user, LatLng latLng) {
        ItemType type = ItemType.values()[typePosition];
        _lostItems.put(name, new Item(name, type, description, user, latLng));
    }

    /**
     * Adds a found item to the lost item collection
     *
     * @param name the name of the new item
     * @param typePosition the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    private void setUpAddFoundItem(String name, int typePosition, String
            description, User user, LatLng latLng) {
        ItemType type = ItemType.values()[typePosition];
        _lostItems.put(name, new Item(name, type, description, user, latLng));
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
            user, LatLng latLng) {
        ItemType type = ItemType.values()[typePosition];
        _lostItems.put(name, new Item(name, type, description, user, latLng));
        Map<String, Object> updates = new HashMap<>();
        updates.put(name, new Item(name, type, description, user, latLng));
        _lostItemsDatabase.updateChildren(updates);
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
            user, LatLng latLng) {
        ItemType type = ItemType.values()[typePosition];
        _foundItems.put(name, new Item(name, type, description, user, latLng));
        Map<String, Object> updates = new HashMap<>();
        updates.put(name, new Item(name, type, description, user, latLng));
        _foundItemsDatabase.updateChildren(updates);
    }

    /**
     * Sets up the ItemManage by adding a listener to the FirebaseDatabase.
     * Anytime anything changes, the corresponding hashmaps will be updated
     */
    void setUp() {
        _lostItemsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String name = (String) snap.child("name").getValue();
                    int type = ItemType.valueOf((String) snap.child("type")
                            .getValue()).ordinal();
                    String description = (String) snap.child("description")
                            .getValue();
                    User user = snap.child("user").getValue(User.class);
                    Double latitude = (Double) snap.child("latLng").child
                            ("latitude").getValue();
                    Double longitude = (Double) snap.child("latLng").child
                            ("longitude").getValue();
                    LatLng latLng = new LatLng(latitude, longitude);
                    setUpAddLostItem(name, type, description, user, latLng);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _foundItemsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String name = (String) snap.child("name").getValue();
                    int type = ItemType.valueOf((String) snap.child("type")
                            .getValue()).ordinal();
                    String description = (String) snap.child("description")
                            .getValue();
                    User user = snap.child("_user").getValue(User.class);
                    Double latitude = (Double) snap.child("latLng").child
                            ("latitude").getValue();
                    Double longitude = (Double) snap.child("latLng").child
                            ("longitude").getValue();
                    LatLng latLng = new LatLng(latitude, longitude);
                    setUpAddFoundItem(name, type, description, user, latLng);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            return _foundItems.get(name).getSearchDescription();
        } else {
            return _lostItems.get(name).getSearchDescription();
        }
    }
}

