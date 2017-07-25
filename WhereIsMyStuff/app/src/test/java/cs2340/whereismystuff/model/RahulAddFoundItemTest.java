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
