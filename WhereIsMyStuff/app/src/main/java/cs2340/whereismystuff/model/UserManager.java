package cs2340.whereismystuff.model;

import java.util.HashMap;

class UserManager {
    private HashMap<String, User> _users;
    private HashMap<String, String> _emailUser;
    private static final UserManager instance = new UserManager();

    private UserManager() {
        _users = new HashMap<>();
        _emailUser = new HashMap<>();
        setUp();
    }

    static UserManager getInstance() {
        return instance;
    }

    private void setUp() {
        User user = new User();
        addUser(user);
    }

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

    private void addUser(User user) {
        _users.put(user.getUsername(), user);
        _emailUser.put(user.getEmail(), user.getUsername());
    }

    int addUser(String firstName, String lastName, String email, String
            username, String password1, String password2) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();
        username = username.trim();
        int code = validateInput(firstName, lastName, email, username,
                password1, password2);
        if (code == 0) {
            addUser(new User(firstName, lastName, email, username, password1));
        }
        return code;
    }

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
            return 0;
        }
    }

    String getName(String usernameEmail) {
        usernameEmail = usernameEmail.trim();
        User user;
        if (usernameEmail.indexOf('@') < 0) {
            user = _users.get(usernameEmail);
        } else {
            user = _users.get(_emailUser.get(usernameEmail));
        }
        if (user != null) {
            return user.getName();
        }
        return "Anonymous";
    }
}
