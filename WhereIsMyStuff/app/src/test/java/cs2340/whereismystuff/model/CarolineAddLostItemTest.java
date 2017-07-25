package cs2340.whereismystuff.model;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import cs2340.whereismystuff.model.Item;
import cs2340.whereismystuff.model.ItemType;
import cs2340.whereismystuff.model.Model;
import cs2340.whereismystuff.model.User;

import static org.junit.Assert.*;

/**
 *
 Contract: addLostItem

 Signature:
 addLostItem(name: String, typePosition: int, description: String, user: User,
 latLng: LatLng) : int

 This method adds a lost item to the database depending on whether or not input
 is valid. It notifies the user that the item they entered was successfully
 entered or what piece of information she or he is missing.
 
 Preconditions:
 User is valid (in terms of the app)
 User is logged in to the app
 User has clicked on map denoting location of lost item
 User has clicked “Enter Item” button on AddLostItem page
 Parameters must match their assigned type (or be null because this is checked
 for in the method)

 Postconditions:
 Item created and added to lost item database if all input is valid (if all
 preconditions are true)
 int is returned depending on whether or not the lost item is correctly added to
 the database
 int notifies controller which pop up message to display to the user

 Invariant:
 None

 Frame Condition:
 validateInput() is called an an integer is returned

 Returns:
 an integer code representing whether or not the item is added correctly
  -> 0 if item successfully added
  -> 1 if user entered invalid name
  -> 2 if user entered invalid type
  -> 3 if user entered invalid description
  -> 4 if user not logged in correctly
  -> 5 if location not valid
  -> 6 if user entered item name already in database

 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//assertEquals(expected, actual)
public class CarolineAddLostItemTest {
    Item item;
    LatLng latLng;
    User user;
    Model model;

    String _name;
    int _type;
    String _description;
    User _user;
    LatLng _latLng;


    @Before
    public void setUp() {
        model = Model.getInstance();
        latLng = new LatLng(0.0, 0.0);
        user = new User();
        _type = 0;
        item = new Item("name", ItemType.OTHER, "description", user, latLng);
    }

    @Test
    public void testAddLostItem_nullName() throws Exception {
        item.setName(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(1, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }

    @Test
    public void testAddLostItem_emptyStrName() throws Exception {
        item.setName("");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(1, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }

    @Test
    public void testAddLostItem_typePositionNeg() throws Exception {
        item.setName("1");
        _type = -5;
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(2, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }

    @Test
    public void testAddLostItem_nullDescription() throws Exception {
        item.setName("2");
        item.setDescription(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(3, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }
    @Test
    public void testAddLostItem_emptyStrDescription() throws Exception {
        item.setName("3");
        item.setDescription("");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(3, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }
    @Test
    public void testAddLostItem_nullUser() throws Exception {
        item.setName("4");
        item.setUser(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(4, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }
    @Test
    public void testAddLostItem_nullLatLng() throws Exception {
        item.setName("5");
        item.setLatLng(null);
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(5, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }
    @Test
    public void testAddLostItem_itemAlreadyExists() throws Exception {
        item.setName("6");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        model.addLostItem(_name, _type, _description, _user,
                _latLng);
        assertEquals(6, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }
    @Test
    public void testAddLostItem_validItemCreated() throws Exception {
        item.setName("7");
        _name = item.getName();
        _description = item.getDescription();
        _user = item.getUser();
        _latLng = item.getLatLng();
        assertEquals(0, model.addLostItem(_name, _type, _description, _user,
                _latLng));
    }


}
