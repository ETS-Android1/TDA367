package edu.stulb.rentalcar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.User;
import edu.stulb.rentalcar.model.user.UserHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void userIsCorrect() {
        //Card test
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        //User test
        User user = new User("Hannes", "Hannes@gmail.com", "Stulb123", card);
        assertEquals(user.getName(), "Hannes");
        assertEquals(user.getEmail(), "Hannes@gmail.com");
        assertEquals(user.getCard().getCardName(), "Hannes Thörn");
        assertEquals(user.getCard().getCardCvv(), "111");
        assertEquals(user.getCard().getCardDate(), "11/25");
        assertEquals(user.getCard().getCardNumber(), "5355830012341234");
        //toHashMap test
        HashMap<String, Object> userHashMap = user.toHashMap();
        HashMap<String, Object> expectedHashMap = new HashMap<>();
        expectedHashMap.put("Name", "Hannes");
        expectedHashMap.put("Email", "Hannes@gmail.com");
        expectedHashMap.put("Password", "Stulb123");
        expectedHashMap.put("CardName", "Hannes Thörn");
        expectedHashMap.put("CardNumber", "5355830012341234");
        expectedHashMap.put("CardDate", "11/25");
        expectedHashMap.put("CardCVV", "111");
        assertEquals(userHashMap, expectedHashMap);
    }

    @Test
    public void userHandlerIsCorrect(){
        //createUser test
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        UserHandler.getInstance().createUser("Hannes", "Hannes@gmail.com", "Stulb123", card);
        User user = UserHandler.getInstance().getUsers().get(0);
        assertEquals(user.getName(), "Hannes");
        assertEquals(user.getEmail(), "Hannes@gmail.com");
        assertEquals(user.getCard().getCardName(), "Hannes Thörn");
        assertEquals(user.getCard().getCardCvv(), "111");
        assertEquals(user.getCard().getCardDate(), "11/25");
        assertEquals(user.getCard().getCardNumber(), "5355830012341234");
        //signIn test
        UserHandler.getInstance().signIn("Hannes@gmail.com", "Stulb123");
        assertTrue(UserHandler.getInstance().isUserSignedIn());
        assertEquals(UserHandler.getInstance().getCurrentUser().getEmail(), "Hannes@gmail.com");
        assertEquals(UserHandler.getInstance().getCurrentUser().getPassword(), "Stulb123");
        //signOut test
        UserHandler.getInstance().signOut();
        assertFalse(UserHandler.getInstance().isUserSignedIn());
        assertNull(UserHandler.getInstance().getCurrentUser());
        //setUsers test
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        UserHandler.getInstance().setUsers(users);
        assertEquals(UserHandler.getInstance().getUsers(), users);
    }

}
