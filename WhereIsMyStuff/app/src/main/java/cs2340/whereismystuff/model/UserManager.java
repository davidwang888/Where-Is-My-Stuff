package cs2340.whereismystuff.model;

import java.util.HashMap;

/**
 * Represents a user manager that stores all of the users and is capable of
 * validating user input and logging users in
 */
class UserManager {

    private User _currentUser;

    /**
     * A HashMap that maps usernames to the the corresponding users
     */
    private HashMap<String, User> _users;

    /**
     * A HashMap that maps a email addresses to the corresponding users'
     * usernames
     */
    private HashMap<String, String> _emailUser;

    /**
     * The singular instance of UserManager that will be used by the Model to
     * manage the users
     */
    private static final UserManager instance = new UserManager();

    /**
     * Creates a UserManager containing only the default administrator
     */
    private UserManager() {
        _users = new HashMap<>();
        _emailUser = new HashMap<>();
        setUp();
    }

    /**
     * Returns the user manager
     * @return The user manager
     */
    static UserManager getInstance() {
        return instance;
    }

    /**
     * Adds the administrator to the new, empty UserManager
     */
    private void setUp() {
        addUser("admin", "one", "admin@gatech.edu", "user", "pass", "pass",
                true);
    }

    /**
     * Determines whether or not the information the user has entered is valid
     * and returns 0 if it is valid and some other number if it is not
     * @param  firstName     The user's first name
     * @param  lastName      The user's last name
     * @param  email         The user's email
     * @param  username      The user's username
     * @param  password1     The user's password
     * @param  password2     Verification of the user's password                                                            password2 [description]
     * @return A number that the controller will eventually use in
     *         RegisterActivity to display a message to the user that will
     *         explain how to fix their input if it is invalid
     *         0 -> user successfully added
     *         1 -> user entered invalid name, cannot register
     *         2 -> user entered invalid email, cannot register
     *         3 -> user entered invalid username, cannot register
     *         4 -> user entered invalid username that contains whitespace chars, cannot register
     *         5 -> user entered invalid username containing @ char, cannot register
     *         6 -> user entered invalid password, cannot register
     *         7 -> user entered passwords that do not match, cannot register
     *         8 -> user entered email already in data base, cannot register
     *         9 -> user entered username already in data base, cannot register
     */
    private int validateInput(String firstName, String lastName, String email,
                              String username, String password1, String
                                      password2) {
        if (firstName == null || lastName == null || firstName.length() == 0
                || lastName.length() == 0) {
            return 1;
        } else if (email == null || email.length() <= 4 || email.indexOf('@')
                < 0) {
            return 2;
        } else if (username == null || username.length() == 0) {
            return 3;
        } else if (username.indexOf(' ') >= 0) {
            return 4;
        } else if (username.indexOf('@') >= 0) {
            return 5;
        } else if (password1 == null || password2 == null || password1
                .length() == 0 || password2.length() == 0) {
            return 6;
        } else if (!password1.equals(password2)) {
            return 7;
        } else if (_emailUser.containsKey(email)) {
            return 8;
        } else if (_users.containsKey(username)) {
            return 9;
        } else {
            return 0;
        }
    }

    /**
     * Takes in all of the information required to create a user, makes a new
     * user, and stores that user's information in _users and _emailUser
     * @param  firstName     The user's first name
     * @param  lastName      The user's last name
     * @param  email         The user's email
     * @param  username      The user's username
     * @param  password1     The user's password
     * @param  password2     Verification of the user's password
     * @return The integer code from validateInput
     */
    int addUser(String firstName, String lastName, String email, String
            username, String password1, String password2, boolean isAdmin) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();
        username = username.trim();
        int code = validateInput(firstName, lastName, email, username,
                password1, password2);
        if (code == 0) {
            _users.put(username, new User(firstName, lastName, email, username,
                    password1, isAdmin));
            _emailUser.put(email, username);
        }
        return code;
    }

    /**
     * Attempts to login the user and returns an integer that will be used by
     * the controller to know what message to display to the user based on the
     * results of their login attempt
     * @param  usernameEmail The username/email the user has entered
     * @param  password      The password the user has entered
     * @return A number that will eventually be used by the controller in
     *         LoginActivity to determine what message to display to the user
     *         0 -> user successfully logged in
     *         1 -> user did not enter username/email and/or password, not logged in
     *         2 -> username not in database, not logged in
     *         3 -> email not in database, not logged in
     *         4 -> username/email and/or password incorrect, not logged in
     */
    int loginUser(String usernameEmail, String password) {
        usernameEmail = usernameEmail.trim();
        User user;
        boolean username;
        if (usernameEmail.indexOf('@') < 0) {
            user = _users.get(usernameEmail);
            username = true;
        } else {
            user = _users.get(_emailUser.get(usernameEmail));
            username = false;
        }
        if (usernameEmail.length() == 0 || password.length() == 0) {
            return 1;
        } else if (user == null && username) {
            return 2;
        } else if (user == null) {
            return 3;
        } else if (!user.checkPassword(password)) {
            return 4;
        } else {
            if (username) {
                _currentUser = _users.get(usernameEmail);
            } else {
                _currentUser = _users.get(_emailUser.get(usernameEmail));
            }
            return 0;
        }
    }

    /**
     * Takes in the user's username or email and uses it to retrieve their name
     * from _users
     * @return The user's name if the user exists or "Anonymous" if they don't
     */
    String getName() {
        if (_currentUser != null) {
            return _currentUser.getName();
        }
        return "Anonymous";
    }

    User getCurrentUser() {
        return _currentUser;
    }
}
