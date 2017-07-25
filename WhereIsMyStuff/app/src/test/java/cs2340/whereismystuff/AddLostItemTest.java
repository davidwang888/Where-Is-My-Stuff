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
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//assertEquals(expected, actual)
public class AddLostItemTest {
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