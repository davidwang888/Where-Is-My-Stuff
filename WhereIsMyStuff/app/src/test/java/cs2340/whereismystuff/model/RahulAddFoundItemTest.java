package cs2340.whereismystuff;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.ItemType;
import cs2340.whereismystuff.model.Model;
import cs2340.whereismystuff.model.User;

import static org.junit.Assert.*;
/**
 * Contract: AddFoundItemTest
 * Signature: addFoundItem(name: String, typePosition: Int,
 * Description: String, user: User, latLng: LatLng): Int
 *
 * The method above adds Found Items to the database.
 * It is dependent on whether or not the input for the function
 * is valid. If the item is successfully entered, it will prompt
 * the user with a successfull added message prompt. However, if
 * not, it will notify the user of the specific item lost.
 *
 * Preconditions:
 *  -The User has been entered validly
 *  -The User is logged into the app
 *  -The User has clicked on the location
 *  of the found item in the app.
 *  -The User has clicked on the "Enter Item": button
 *  in the AddFoundItem page
 *  -Parameters must match their assigned types.
 *
 * Postconditions:
 * The item is created and added to the found items database if
 * all the inputs are valid, and if all the preconditions are
 * true. An integer is returned depending on whether the found item
 * has been correctly added to the database. The integer value
 * returned notifies the controller of which Toast error message
 * to throw at the user
 *
 * Invariant:
 * - None
 *
 * Frame Condition:
 * - validateInput() is called, and an integer is returned
 *
 * Returns:
 *  - 0: Item successfully added
 *  - 1: User enters invalid name
 *  - 2: User enters invalid type
 *  - 3: User enters invalid description
 *  - 4: User not logged in correctly
 *  - 5: Location not valid
 *  - 6: Item name is already in the database
 *
 *

 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//assertEquals(expected, actual)
public class RahulAddFoundItemTest {

    Item item;
    LatLng testLatLng;
    User testUser;
    Model model;

    String _name;
    int _type;
    String _description;
    User _user;
    LatLng _latLng;

    @Before
    public void setUp() {
        model = Model.getInstance();
        testUser = new User();
        _type = 0;
        testLatLng = new LatLng(0, 0);

        item = new Item("name", ItemType.OTHER, "description", testUser, testLatLng);

    }

    @Test(expected = Exception.class)
    public void testAddFoundItem_nullName() throws Exception {
        item.setName(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(1, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }

    @Test
    public void testAddFoundItem_emptyStringName() {
        item.setName("");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(model.addFoundItem(_name, _type, _description, _user, _latLng), 1);
    }

    @Test
    public void testAddFoundItem_typePosNeg() throws Exception {
        item.setName("Func 3");
        _type = -2;
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(2, model.addFoundItem(_name, _type, _description, _user, _latLng));

    }

    @Test(expected = Exception.class)
    public void testAddFoundItem_nullDescription() {
        item.setName("Func 4");
        item.setDescription(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(3, model.addFoundItem(_name, _type, _description, _user, _latLng));

    }

    @Test
    public void testAddFoundItem_emptyStringDescription() throws Exception {
        item.setName("Func 5");
        item.setDescription("");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(3, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }

    @Test
    public void testAddFoundItem_userNull() throws Exception {
        item.setName("Func 6");
        item.setUser(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(4, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }

    @Test
    public void testAddFoundItem_nullCoordinate() throws Exception {
        item.setName("Func 7");
        item.setLatLng(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(5, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }

    @Test
    public void testAddFoundItem_repeatItem() throws Exception {
        item.setName("Func 8");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();

        model.addFoundItem(_name, _type, _description, _user, _latLng);
        assertEquals(6, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }

    @Test
    public void testAddFoundItem_itemFullyCreated() throws Exception {
        item.setName("Func 9");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(0, model.addFoundItem(_name, _type, _description, _user, _latLng));
    }


}
