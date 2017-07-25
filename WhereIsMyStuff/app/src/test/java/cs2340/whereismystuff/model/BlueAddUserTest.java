/**
 * Created by georg on 7/19/2017.
 */
package cs2340.whereismystuff.model;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

import cs2340.whereismystuff.model.UserManager;

import static org.junit.Assert.*;

/* Method Contract:

Operation Signature:
validateInput(String firstName, String lastName, String email,
              String username, String password1, String password2)
              where validateInput is a public instance method of UserManager class
              and firstName, lastName, email, username, password1, password2 are instances
              of type String. The method returns a integer that controller will use in
              RegisterActivity to display a message to the user that will explain
              how to fix their input if it is invalid.
Preconditions: None.
Postconditions: Original contents unchanged. An integer is returned that will be passed
                on to RegisterActivity that either signifies successful registering or
                invalid user input.
                If firstName and/or lastName is null or of length 0, the method returns 1.
                If email is null, less than or equal to 4 characters, or doesn't have an @
                symbol, the method returns 2.
                If username is null or of length 0, the method return 3.
                If username has spaces, the method return 4.
                If username has the character '@', the method return 5.
                If password1 or password2 is null or of length 0, the method return 6.
                If password1 is not equal to password2, the method return 7.
                If emailUser doesn't contains the inputted email, the method return 8.
                If username is already in users, the method return 9.
                If the inputs passed all 9 previous conditions, the method return 0.
Invariant: None. Data is analyzed but not changed.
Frame Condition: If input information is valid, an integer will be passed on to RegisterActivity.

*/
public class BlueAddUserTest {

    private static final int TIMEOUT = 3000;
    private static UserManager userManager = UserManager.getInstance();


    @Before
    public void setUp() {
    }

    @Test(timeout = TIMEOUT)
    public void validateInputUser() {

        assertEquals(userManager.addUser(null, "Lin", "glin39@gatech.edu", "glin", "pass", "pass", true), 1);
        assertEquals(userManager.addUser("Georgianna", null, "glin39@gatech.edu", "glin", "pass", "pass" , true),1);
        assertEquals(userManager.addUser(null, null, "glin39@gatech.edu", "glin", "pass", "pass", true),1);
        assertEquals(userManager.addUser("", null, "glin39@gatech.edu", "glin", "pass", "pass", true),1);
        assertEquals(userManager.addUser(null, "", "glin39@gatech.edu", "glin", "pass", "pass", true),1);
        assertEquals(userManager.addUser("Georgianna", "", "glin39@gatech.edu", "glin", "pass", "pass", true),1);
        assertEquals(userManager.addUser("", "Lin", "glin39@gatech.edu", "glin", "pass", "pass", true),1);
        assertEquals(userManager.addUser("", "", "glin39@gatech.edu", "glin", "pass", "pass", true),1);

        assertEquals(userManager.addUser("Georgianna", "Lin", null, "glin", "pass", "pass", true),2);
        assertEquals(userManager.addUser("Georgianna", "Lin", "@ga", "glin", "pass", "pass", true),2);
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39", "glin", "pass", "pass", true),2);
        assertEquals(userManager.addUser("Georgianna", "Lin", "@", "glin", "pass", "pass", true),2);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu", null, "pass", "pass", true),3);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu", "   a", "pass", "pass", true),4);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39@", "pass", "pass", true),5);
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","@", "pass", "pass", true),5);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", null, "pass", true),6);
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", null, null, true),6);
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", "pass", null, true),6);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", "pass", "pass1", true),7);
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", "pass1", "pass", true),7);

        //successful add
        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","glin39", "pass", "pass", true),0);

        assertEquals(userManager.addUser("Georgianna", "Lin", "glin39@gatech.edu","some39", "pass", "pass", true),8);
        assertEquals(userManager.addUser("Georgianna", "Lin", "some39@gatech.edu","glin39", "pass", "pass", true),9);


    }

}
