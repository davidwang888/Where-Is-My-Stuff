package cs2340.whereismystuff.model;

import org.junit.Test;

import cs2340.whereismystuff.model.Model;

import static org.junit.Assert.*;

/**
 * JUnit to test the loginUser method in the UserManager class
 * @author David Wang
 * @version 1.0
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
