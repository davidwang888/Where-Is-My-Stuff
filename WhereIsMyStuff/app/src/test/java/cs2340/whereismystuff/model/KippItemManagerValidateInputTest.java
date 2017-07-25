package cs2340.whereismystuff.model;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by kippmorris on 7/20/17.
 */

// method contract

// private int validateInput(String name, int typePosition, String description,
//     User user, LatLng latLng, boolean lostItem)
//
// preconditions:
//     -the variable "name" is a string of nonzero length, the empty string, or
//      null
//     -the variable "typePosition" is an integer that is either a valid index into
//      our array containing ItemTypes or an invalid index
//     -the variable "description" is a string of nonzero length, the empty string,
//      or null
//     -the variable "user" is either a non-null object of type User or null
//     -the variable "latlng" is either a non-null object of type LatLng or null
//     -the variable "lostItem" is true if the item the item the user is attempting
//      to add is a lost item or false if the item the user is attempting to add is
//      a found item
//
// postconditions:
//     -if "name" is null or the empty string -> return 1
//     -if "name" is a string of nonzero length but "typePosition" is less than
//      zero or greater than or equal to the number of entries in our array
//      containing ItemTypes -> return 2
//     -if "name" is a string of nonzero length, "typePosition" is between zero
//      and the length of our array containing ItemTypes (not including the
//      length), and description is null or the empty string -> return 3
//     -if "name" is a string of nonzero length, "typePosition" is between zero
//      and the length of our array containing ItemTypes (not including the
//      length), "description" is a string of nonzero length, and "user" is null
//      -> return 4
//     -if "name" is a string of nonzero length, "typePosition" is between zero
//      and the length of our array containing ItemTypes (not including the
//      length), "description" is a string of nonzero length, "user" is a non-null
//      object of type User, and "latLng" is null -> return 5
//     -if "name" is a string of nonzero length, "typePosition" is between zero
//      and the length of our array containing ItemTypes (not including the
//      length), "description" is a string of nonzero length, "user" is a non-null
//      object of type User, "latLng" is a non-null object of type LatLng, and
//      either "lostItem" is true and our HashMap mapping names of lost items to
//      items contains the key "name" or "lostItem" is false and our HashMap
//      mapping names of found items to items contains the key "name", -> return 6
//     -if "name" is a string of nonzero length, "typePosition" is between zero
//      and the length of our array containing ItemTypes (not including the
//      length), "description" is a string of nonzero length, "user" is a non-null
//      object of type User, "latLng" is a non-null object of type LatLng, and
//      either "lostItem" is true and our HashMap mapping names of lost items to
//      items does not contain the key "name" or "lostItem" is false and our
//      HashMap mapping names of found items to items does not contain the key
//      "name", -> return 0
//
// invariants:
//     -none
//
// framing conditions:
//     -if the input is all valid, 0 will be returned and the item will be added
//     -if the input is not valid, a different integer will be returned
//      representing an error to display to the user based on which part of their
//      input is invalid


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
