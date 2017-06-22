package cs2340.whereismystuff.model;

/**
 * Represents a user and contains all of the information about that user
 */
class User {

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
