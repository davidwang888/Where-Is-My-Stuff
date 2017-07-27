package cs2340.whereismystuff.model;

/**
 * Represents a user and contains all of the information about that user
 */
public class User {
    /**
     * The user's first name
     */
    private String _firstName;

    /**
     * The user's last name
     */
    private String _lastName;

    /**
     * The user's email
     */
    private String _email;

    /**
     * The user's username
     */
    private String _username;

    /**
     * The user's password
     */
    private String _password;

    /**
     * The boolean representing whether the user is an admin or not
     */
    private boolean _isAdmin;

    /**
     * Creates a user with the information passed in
     *
     * @param firstName the new user's first name
     * @param lastName the new user's last name
     * @param email the new user's email
     * @param username the new user's username
     * @param password the new user's password
     * @param isAdmin the boolean representing whether the new user is an
     *                admin or not
     */
    User(String firstName, String lastName, String email, String username,
         String password, boolean isAdmin) {
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _username = username;
        _password = password;
        _isAdmin = isAdmin;
    }

    /**
     * Default constructor for FireBase
     */
    public User() {
    }

    /**
     * Gets the first name of the user
     *
     * @return the first name
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * Gets the last name of the user
     *
     * @return the last name
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * Gets the email of the user
     *
     * @return the email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Gets the username of the user
     *
     * @return the username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Gets the password of the user
     *
     * @return the password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Gets the isAdmin attribute of the user
     *
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return _isAdmin;
    }

    /**
     * Sets the first name of the user
     * @param _firstName the new first name of the user
     */
    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    /**
     * Sets the last name of the user
     * @param _lastName the new last name of the user
     */
    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    /**
     * Sets the email of the user
     * @param _email the new email of the user
     */
    public void setEmail(String _email) {
        this._email = _email;
    }

    /**
     * Sets the username of the user
     * @param _username the new username of the user
     */
    public void setUsername(String _username) {
        this._username = _username;
    }

    /**
     * Sets the password of the user
     * @param _password the new password of the user
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * Sets the isAdmin attribute of the user
     * @param _isAdmin the new isAdmin attribute of the user
     */
    public void setIsAdmin(boolean _isAdmin) {
        this._isAdmin = _isAdmin;
    }


    /**
     * Returns the user's first and last name if they have a last name or just
     * their first name if they don't
     *
     * @return The user's name
     */
    String getName() {
        return (_lastName.length() == 0) ? _firstName :  _firstName + " " +
                _lastName;
    }

    /**
     * Checks to see if the user has entered their password correctly
     *
     * @param  password      The password the user has entered
     * @return A boolean representing whether or not the user entered their
     *         password correctly
     */
    boolean checkPassword(String password) {
        return password.equals(_password);
    }
}
