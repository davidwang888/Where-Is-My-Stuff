package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Reprsents a item manager that stores all the lost and found items and is
 * used to search for items
 */
public class ItemManager {
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
//    private DatabaseReference _databaseRef;

    /**
     * The database reference to the lost item section of the Firebase database
     */
//    private DatabaseReference _lostItemsDatabase;

    /**
     * The database reference to the found item section of the Firebase database
     */
//    private DatabaseReference _foundItemsDatabase;

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
//        _databaseRef = FirebaseDatabase.getInstance().getReference();
//        _lostItemsDatabase = _databaseRef.child("lost items");
//        _foundItemsDatabase = _databaseRef.child("found items");
    }

    /**
     * Returns the single instance of item manager
     * @return The item manager
     */
    static ItemManager getInstance() {
        return instance;
    }

    /**
     * Determines whether or not the information the user has entered for an
     * item is valid and returns 0 if it is valid and som eother number if it
     * is not
     *
     * @param name The item's name
     * @param typePosition The item's type
     * @param description The item's description
     * @param user The item's user
     * @param latLng The item's location
     * @return A number that the controller will eventually use in
     * EnterLostItemActivity or EnterFoundItemActivity to display a message
     * to the user that will explain how to fix their input if it is invalid
     * 0 -> item successfully added
     * 1 -> user entered invalid name, cannot add item
     * 2 -> user entered invalid type, cannot add item
     * 3 -> user entered invalid description, cannot add item
     * 4 -> user not logged in correctly, cannot add item
     * 5 -> location not valid, cannot add item
     * 6 -> user entered item name already in data base, cannot add item
     */
    private int validateInput(String name, int typePosition, String
            description, User user, LatLng latLng, boolean lostItem) {
        if (name == null || name.length() == 0) {
            return 1;
        } else if (typePosition < 0 || typePosition >= ItemType.values()
                .length) {
            return 2;
        } else if (description == null || description.length() == 0) {
            return 3;
        } else if (user == null) {
            return 4;
        } else if (latLng == null) {
            return 5;
        } else if (lostItem && _lostItems.containsKey(name) || !lostItem &&
                _foundItems.containsKey(name)) {
            return 6;
        } else {
            return 0;
        }
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
        _foundItems.put(name, new Item(name, type, description, user, latLng));
    }

    /**
     * Sets up the ItemManage by adding a listener to the FirebaseDatabase.
     * Anytime anything changes, the corresponding hashmaps will be updated
     */
    void setUp() {
//        _lostItemsDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    String name = (String) snap.child("name").getValue();
//                    int type = ItemType.valueOf((String) snap.child("type")
//                            .getValue()).ordinal();
//                    String description = (String) snap.child("description")
//                            .getValue();
//                    User user = snap.child("user").getValue(User.class);
//                    Double latitude = (Double) snap.child("latLng").child
//                            ("latitude").getValue();
//                    Double longitude = (Double) snap.child("latLng").child
//                            ("longitude").getValue();
//                    LatLng latLng = new LatLng(latitude, longitude);
//                    setUpAddLostItem(name, type, description, user, latLng);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        _foundItemsDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    String name = (String) snap.child("name").getValue();
//                    int type = ItemType.valueOf((String) snap.child("type")
//                            .getValue()).ordinal();
//                    String description = (String) snap.child("description")
//                            .getValue();
//                    User user = snap.child("_user").getValue(User.class);
//                    Double latitude = (Double) snap.child("latLng").child
//                            ("latitude").getValue();
//                    Double longitude = (Double) snap.child("latLng").child
//                            ("longitude").getValue();
//                    LatLng latLng = new LatLng(latitude, longitude);
//                    setUpAddFoundItem(name, type, description, user, latLng);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    /**
     * Adds a lost item to the lost item collection
     *
     * @param name the name of the new item
     * @param typePosition the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    int addLostItem(String name, int typePosition, String description, User
            user, LatLng latLng) {
        if (!(name == null)) {
            name = name.trim();
        }
        if (!(description == null)) {
            description = description.trim();
        }
        int code = validateInput(name, typePosition, description, user,
                latLng, true);
        if (code == 0) {
            ItemType type = ItemType.values()[typePosition];
            _lostItems.put(name, new Item(name, type, description, user, latLng));
            Map<String, Object> updates = new HashMap<>();
            updates.put(name, new Item(name, type, description, user, latLng));
//            _lostItemsDatabase.updateChildren(updates);
        }
        return code;
    }

    /**
     * Adds a found item to the lost item collection
     *
     * @param name the name of the new item
     * @param typePosition the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    int addFoundItem(String name, int typePosition, String description, User
            user, LatLng latLng) {
        name = name.trim();
        description = description.trim();
        int code = validateInput(name, typePosition, description, user,
                latLng, false);
        if (code == 0) {
            ItemType type = ItemType.values()[typePosition];
            _foundItems.put(name, new Item(name, type, description, user, latLng));
            Map<String, Object> updates = new HashMap<>();
            updates.put(name, new Item(name, type, description, user, latLng));
//            _foundItemsDatabase.updateChildren(updates);
        }
        return code;
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
     * @param lostItem whether the item is a lost item or not
     * @param name the name of the item
     * @return a boolean whether the item is found
     */
    boolean searchFound(Boolean lostItem, String name) {
        if (lostItem) {
            return _lostItems.get(name) != null;
        } else {
            return _foundItems.get(name) != null;
        }
    }

    /**
     * Looks for an item and returns the item from the item manager
     *
     * @param lostItem whether the item is a found item or not
     * @param name the name of the item
     * @return a boolena whether the item is found
     */
    String searchResult(Boolean lostItem, String name) {
        System.out.println("ItemManager/searchResult: name is " + name);
        Item item = (lostItem) ? _lostItems.get(name) : _foundItems.get(name);
        if (lostItem) {
            return "LOST ITEM:\n" + _lostItems.get(name).getSearchDescription();
        } else {
            return "FOUND ITEM:\n" + _foundItems.get(name).getSearchDescription();
        }
    }
}
