package cs2340.whereismystuff.model;

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
     * The singular instance of this class that will be used by other classes
     */
    private static final Model instance = new Model();

    /**
     * Returns the model
     * @return the model
     */
    public static Model getInstance() {
        return instance;
    }

    /**
     * Adds the user's information to the appropriate variables in UserManager
     * and communicates with the controller to let it know what to tell the user
     * @param  String firstName     The user's first name
     * @param  String lastName      The user's last name
     * @param  String email         The user's email address
     * @param  String username      The user's username
     * @param  String password1     The user's password
     * @param  String password2     The user's verification of their password
     * @return A number between 0 and 9 based on the validity of the user's
     *         information; this number will be used by the controller in
     *         RegisterActivity to determine what message will be displayed to
     *         the user about the information they have entered
     */
    public int addUser(String firstName, String lastName, String email, String
            username, String password1, String password2) {
        return userManager.addUser(firstName, lastName, email, username,
                password1, password2);
    }

    /**
     * Attempts to login the user using the username/email and password they
     * have entered and then communicates with the controller to let it know
     * what to tell the user and what activity to display
     * @param  String usernameEmail The username/email entered by the user
     * @param  String password      The password entered by the user
     * @return A number that will be used in the controller by LoginActivity to
     *         determine what message to display to the user about the results
     *         of the login attempt
     */
    public int loginUser(String usernameEmail, String password) {
        return userManager.loginUser(usernameEmail, password);
    }

    /**
     * Takes in the user's email and lets the UserManager use it to find and
     * return the corresponding user's username
     * @param  String usernameEmail The username/email of the user in question
     * @return The user's name
     */
    public String getName(String usernameEmail) {
        return userManager.getName(usernameEmail);
    }
}
