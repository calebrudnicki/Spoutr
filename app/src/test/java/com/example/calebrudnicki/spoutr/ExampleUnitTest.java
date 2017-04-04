package com.example.calebrudnicki.spoutr;

import android.location.Geocoder;
import android.location.Location;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

//    @Before
//    public void setUp() { sraClass = new SubmitReportActivity(); }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addUserTest() throws Exception {
        Model model = new Model();
        //Testing for a successful user
        User u1 = new User("Caleb Rudnicki", "calebrudnicki@gmail.com", "crudnicki", "calebrudnicki", "User");
        assertTrue("Added user successfully", model.addUser(u1));
        //Testing for a short name
        User u2 = new User("Ca", "calebrudnicki@gmail.com", "crudnicki", "calebrudnicki", "User");
        assertFalse("Added user with a name too short", model.addUser(u2));
        //Testing for an email without a @
        User u3 = new User("Caleb Rudnicki", "calebrudnickigmail.com", "crudnicki", "calebrudnicki", "User");
        assertFalse("Added user with a email with no @", model.addUser(u3));
        //Testing for a short email
        User u4 = new User("Caleb Rudnicki", "c@c.ca", "crudnicki", "calebrudnicki", "User");
        assertFalse("Added user with a email with too short", model.addUser(u4));
        //Testing for a short password
        User u5 = new User("Caleb Rudnicki", "calebrudnicki@gmail.com", "cru", "calebrudnicki", "User");
        assertFalse("Added user with a password with too short", model.addUser(u5));
        //Testing for a short username
        User u6 = new User("Caleb Rudnicki", "calebrudnicki@gmail.com", "crudnicki", "ca", "User");
        assertFalse("Added user with a username with too short", model.addUser(u6));
        //Testing to see if only one user has been added so far
        assertEquals(1, model.getAllUsers().size());
        //Testing a user with a prexisting username
        User u7 = new User("Caleb Rudnicki", "calebrudnicki@gmail.com", "crudnicki", "calebrudnicki", "User");
        assertFalse("Added user with a preexisting username", model.addUser(u7));
        assertEquals(1, model.getAllUsers().size());

    }
}