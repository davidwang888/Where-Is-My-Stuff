package cs2340.whereismystuff.model;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by kippmorris on 7/20/17.
 */

public class KippItemManagerValidateInputTest {

    private static ItemManager itemManager = ItemManager.getInstance();

    @Test
    public void validateInput_NullOrEmptyName_Returns1() {
        assertEquals(itemManager.addLostItem(null, 0, "", null, null), 1);
        assertEquals(itemManager.addLostItem("", 0, "", null, null), 1);
    }

    @Test
    public void validateInput_InvalidTypePosition_Returns2() {
        assertEquals(itemManager.addLostItem("name", -12, "", null, null), 2);
        assertEquals(itemManager.addLostItem("name", 12,
                null, null, null), 2);
    }

    @Test
    public void validateInput_NullOrEmptyDescription_Returns3() {
        assertEquals(itemManager.addLostItem("name",
                ItemType.values().length - 1, null, null, null), 3);
        assertEquals(itemManager.addLostItem("name",
                ItemType.values().length - 1, "", null, null), 3);
    }

    @Test
    public void validateInput_NullUser_Returns4() {
        assertEquals(itemManager.addLostItem("name", ItemType.values().length - 1,
                "description", null, null), 4);
    }

    @Test
    public void validateInput_NullLatLng_Returns5() {
        assertEquals(itemManager.addLostItem("name",
                ItemType.values().length - 1, "description", new User("first",
                        "last", "email@aol.com", "username", "password", true), null),
                5);
    }

    @Test
    public void validateInput_ItemNameHasAlreadyBeenUsed_Returns6() {
        itemManager.addLostItem("computer", 0, "description", new User("first",
                        "last", "email@aol.com", "username", "password", true),
                new LatLng(12.6, 12.6));
        assertEquals(itemManager.addLostItem("computer", 0, "description",
                new User("first", "last", "email@aol.com", "username", "password",
                        true), new LatLng(12.6, 12.6)), 6);
        itemManager.addFoundItem("iphone", 0, "description", new User("first",
                        "last", "email@aol.com", "username", "password", true),
                new LatLng(12.6, 12.6));
        assertEquals(itemManager.addFoundItem("iphone", 0, "description",
                new User("first", "last", "email@aol.com", "username", "password",
                        true), new LatLng(12.6, 12.6)), 6);
    }

    @Test
    public void validateInput_CorrectInput_Returns0() {
        assertEquals(itemManager.addLostItem("puppy", 4, "description",
                new User("first", "last", "email@aol.com", "username", "password",
                        true), new LatLng(12.6, 12.6)), 0);
    }
}
