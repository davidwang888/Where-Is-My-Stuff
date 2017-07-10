package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Represents a singular instance of the model that the entire project uses to
 * communicate with the backend
 */
public class Model {
    /**
     * The one UserManager that will be used by this class to keep track of the
     * different users of the application
     */
    private static final UserManager userManager = UserManager.getInstance();

    /**
     * The one ItemManager that will be used by this class to keep track of the
     * different items of the application
     */
    private static final ItemManager itemManager = ItemManager.getInstance();

    /**
     * The singular instance of this class that will be used by other classes
     */
    private static final Model instance = new Model();

    /**
     * Returns the model
     *
     * @return the model
     */
    public static Model getInstance() {
        return instance;
    }

    public void setUp() {
        itemManager.setUp();
    }

    /**
     * Adds the user's information to the appropriate variables in UserManager
     * and communicates with the controller to let it know what to tell the user
     *
     * @param  firstName     The user's first name
     * @param  lastName      The user's last name
     * @param  email         The user's email address
     * @param  username      The user's username
     * @param  password1     The user's password
     * @param  password2     The user's verification of their password
     * @return A number between 0 and 9 based on the validity of the user's
     *         information; this number will be used by the controller in
     *         RegisterActivity to determine what message will be displayed to
     *         the user about the information they have entered
     */
    public int addUser(String firstName, String lastName, String email, String
            username, String password1, String password2, boolean isAdmin) {
        return userManager.addUser(firstName, lastName, email, username,
                password1, password2, isAdmin);
    }

    /**
     * Attempts to login the user using the username/email and password they
     * have entered and then communicates with the controller to let it know
     * what to tell the user and what activity to display
     * @param usernameEmail The username/email entered by the user
     * @param password      The password entered by the user
     * @return A number that will be used in the controller by LoginActivity to
     *         determine what message to display to the user about the results
     *         of the login attempt
     */
    public int loginUser(String usernameEmail, String password) {
        return userManager.loginUser(usernameEmail, password);
    }

    /**
     * Provides the current user's name
     *
     * @return The user's name
     */
    public String getName() {
        return userManager.getName();
    }

    /**
     * Provides the current user logged in
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }

    /**
     * Provides an array of all the item types
     *
     * @return an array of all the item types
     */
    public ItemType[] getItemTypes() {
        return ItemType.values();
    }

    /**
     * Adds a lost item to the lost item collection
     *
     * @param name the name of the new item
     * @param type the int position of the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    public void addLostItem(String name, int type, String description, User
            user, LatLng latLng) {
        itemManager.addLostItem(name, type, description, user, latLng);
    }

    /**
     * Adds a lost item to the found item collection
     *
     * @param name the name of the new item
     * @param type the int position of the type of the new item
     * @param description the description of the new item
     * @param user the user who entered the new item
     */
    public void addFoundItem(String name, int type, String description,
                             User user, LatLng latLng) {
        itemManager.addFoundItem(name, type, description, user, latLng);
    }

    /**
     * Returns a list of all the lost items for the list view
     *
     * @return a list of all the lost items
     */
    public List<Item> getLostItems() {
        return itemManager.getLostItems();
    }

    /**
     * Returns a list of all the found items for the list view
     *
     * @return a list of all the found items
     */
    public List<Item> getFoundItems() {
        return itemManager.getFoundItems();
    }

    /**
     * Looks for an item and returns whether the item is stored in the item
     * manager
     *
     * @param foundItem whether the item is a found item or not
     * @param name the name of the item
     * @return a boolean whether the item is found
     */
    public boolean searchFound(Boolean foundItem, String name) {
        return itemManager.searchFound(foundItem, name);
    }

    /**
     * Looks for an item and returns the item from the item manager
     *
     * @param foundItem whether the item is a found item or not
     * @param name the name of the item
     * @return a boolean whether the item is found
     */
    public String searchResult(Boolean foundItem, String name) {
        return itemManager.searchResult(foundItem, name);
    }
}
