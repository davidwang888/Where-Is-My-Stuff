package cs2340.whereismystuff.model;

/**
 * Represents a user and contains all of the information about that user
 */
class User {

    /**
     * The user's information
     */
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _username;
    private String _password;
    private boolean _isAdmin;

    /**
     * Creates a user with the information passed in
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
     * Creates the administrator using a default set of information
     */
    User() {
        this("admin", "", "admin@gatech.edu", "user", "pass", true);
    }

    /**
     * Returns the user's first and last name if they have a last name or just
     * their first name if they don't
     * @return The user's name
     */
    String getName() {
        return (_lastName.length() == 0) ? _firstName :  _firstName + " " +
                _lastName;
    }

    /**
     * Returns the user's email address
     * @return The user's email address
     */
    String getEmail() {
        return _email;
    }

    /**
     * Returns the user's username
     * @return The user's username
     */
    String getUsername() {
        return _username;
    }

    /**
     * Checks to see if the user has entered their password correctly
     * @param  password      The password the user has entered
     * @return A boolean representing whether or not the user entered their
     *         password correctly
     */
    boolean checkPassword(String password) {
        return password.equals(_password);
    }
}
