package cs2340.whereismystuff.model;

import org.junit.Test;

import cs2340.whereismystuff.model.Model;

import static org.junit.Assert.*;

/**
 * JUnit to test the loginUser method in the UserManager class
 * @author David Wang
 * @version 1.0
 *
 * Signature:
 * int loginUser(String usernameEmail, String password)
 *
 * Description:
 * The method logs in a user by validating the usernameEmail and password
 * parameters. If the parameters are valid, the method sets the current user
 * in the UserManager class to the logged in user. This method is in
 * UserManager. However, it is accessed through the Model class (which has a
 * public method with the same signature that simply calls the package
 * private method in UserManager).
 *
 * Precondition:
 * Parameters (usernameEmail and password) are not null
 *
 * Postcondition:
 * If the usernameEmail and password are valid (not only are they valid
 * Strings for usernameEmail and password, they correspond according to the
 * map of registered users), the method sets the current user in the
 * UserManager class to the user with information corresponding to the
 * parameters passed in.
 *
 * Framing Conditions:
 * The UserManager's current user will change if the parameters passed in are
 * valid. An error code integer is always returned.
 *
 * Invariants:
 * None
 *
 * Returns:
 * The method returns an error code depending on what went wrong. An error
 * code of 0 represents a successful login. An error code of 1 represents
 * invalid Strings for usernameEmail or password (the parameters are empty
 * strings). An error code of 2 represents a username that is passed in but
 * not in the database. An error code of 3 represents an email that is passed
 * in but not in the database. An error code of 4 represents a username or
 * email that is in the database, but the password does not match according
 * to the map of registered users. The caller of the method will use this
 * error code to decide how to handle the valid or invalid parameters.
 */
public class DavidLoginUserTest {
    private static final int TIMEOUT = 200;
    private Model model = Model.getInstance();

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameEmptyPasswordValid() {
        assertEquals(1, model.loginUser("", "pass"));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameEmptyPasswordEmpty() {
        assertEquals(1, model.loginUser("", ""));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameValidPasswordEmpty() {
        assertEquals(1, model.loginUser("user", ""));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameNonexistantPasswordValid() {
        assertEquals(2, model.loginUser("MadeUpUser", "pass"));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameNonexistantPasswordInvalid() {
        assertEquals(2, model.loginUser("MadeUpUser", "WrongPassword"));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameValidPasswordIncorrect() {
        assertEquals(4, model.loginUser("user", "WrongPassword"));
    }

    @Test(timeout = TIMEOUT)
    public void testUsernameInputUsernameValidPasswordValid() {
        assertEquals(0, model.loginUser("user", "pass"));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailEmptyPasswordValid() {
        assertEquals(1, model.loginUser("", "pass"));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailEmptyPasswordEmpty() {
        assertEquals(1, model.loginUser("", ""));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailValidPasswordEmpty() {
        assertEquals(1, model.loginUser("admin@gatech.edu", ""));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailNonexistantPasswordValid() {
        assertEquals(3, model.loginUser("MadeUp@mail.com", "pass"));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailNonexistantPasswordInvalid() {
        assertEquals(3, model.loginUser("MadeUp@mail.com", "WrongPassword"));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailValidPasswordIncorrect() {
        assertEquals(4, model.loginUser("admin@gatech.edu", "WrongPassword"));
    }

    @Test(timeout = TIMEOUT)
    public void testEmailInputEmailValidPasswordValid() {
        assertEquals(0, model.loginUser("admin@gatech.edu", "pass"));
    }
}
