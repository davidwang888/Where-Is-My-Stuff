package cs2340.whereismystuff.model;

class User {
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _username;
    private String _password;

    User(String firstName, String lastName, String email, String username,
         String password) {
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _username = username;
        _password = password;
    }

    User() {
        this("admin", "", "admin@gatech.edu", "user", "pass");
    }

    String getName() {
        return (_lastName.length() == 0) ? _firstName :  _firstName + " " +
                _lastName;
    }

    String getEmail() {
        return _email;
    }

    String getUsername() {
        return _username;
    }

    boolean checkPassword(String password) {
        return password.equals(_password);
    }
}
