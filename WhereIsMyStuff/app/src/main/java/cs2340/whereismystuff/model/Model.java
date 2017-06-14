package cs2340.whereismystuff.model;

public class Model {
    private static final UserManager userManager = UserManager.getInstance();
    private static final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    public int addUser(String firstName, String lastName, String email, String
            username, String password1, String password2) {
        return userManager.addUser(firstName, lastName, email, username,
                password1, password2);
    }

    public int loginUser(String usernameEmail, String password) {
        return userManager.loginUser(usernameEmail, password);
    }

    public String getName(String usernameEmail) {
        return userManager.getName(usernameEmail);
    }
}
