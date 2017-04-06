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

    @Test
    public void addToPRListTest() throws Exception {

        User user = new User("Kendal Lin", "klin78@gatech.edu", "kmoneydelicious", "kendallin", "User");
        Location Texas = new Location("Texas");
        WaterReport report = new WaterReport(user, "04/29/1997 10:42:54", Texas, "Texas", "Well", "good condition");
        //Testing for adding purity report to list successfully
        PurityReport p1 = new PurityReport(user, "04/29/1997 10:42:54", Texas, "good condition", 6, 9);
        assertTrue("Another Purity Report added to list successfully", report.addToPRList(p1));
        //Testing for null user
        PurityReport p2 = new PurityReport(null, "04/29/1997 10:42:54", Texas, "good condition", 6, 9);
        assertFalse("Purity Report added with null user", report.addToPRList(p2));
        //Testing for null dateSubmitted
        PurityReport p3 = new PurityReport(user, null, Texas, "good condition", 6, 9);
        assertFalse("Purity Report added with null dateSubmitted", report.addToPRList(p3));
        //Testing for null condition
        PurityReport p4 = new PurityReport(user, "04/29/1997 10:42:54", Texas, null, 6, 9);
        assertFalse("Purity Report added with null condition", report.addToPRList(p4));
        //Testing if virusPPM is less than 0
        PurityReport p5 = new PurityReport(user, "04/29/1997 10:42:54", Texas, "good condition", -1, 9);
        assertFalse("Purity Report added with a negative virusPPM", report.addToPRList(p5));
        //Testing if contaminantPPM is less than 0
        PurityReport p6 = new PurityReport(user, "04/29/1997 10:42:54", Texas, "good condition", 6, -1);
        assertFalse("Purity Report added with a negative contaminantPPM", report.addToPRList(p6));
    }

    @Test
    public void addPurityReportTest() throws Exception {
        Model model = new Model();
        User user = new User("Chloe Belangia", "chloe.belangia@gmail.com", "cbelangia", "chloebelangia", "User");
        Location loc = new Location("Here");
        //Testing for creating a successful purity report
        PurityReport pr = new PurityReport(user, "today", loc, "good condition", 69, 69);
        assertTrue("Added Purity Report Successfully", model.addPurityReport(pr));
        //Testing for null location
        PurityReport prWrong = new PurityReport(user, "today", null, "good condition", 69, 69);
        assertFalse("Location cannot be null", model.addPurityReport(prWrong));

    }

    @Test
    public void addWaterReportTest() {
        Model model = new Model();
        Location loomischaffee = new Location("Loomis Chaffee");
        Location ocala = new Location("Ocala");
        User bob = new User("Bob Waters", "bwater@gatech.edu", "bobwaters", "csiffun", "User");
        User jack = new User("Jack McCormack", "jmack@gatech.edu", "jmccormack30", "qwerty", "User");
        WaterReport r1 = new WaterReport(bob, "12/15/1996 14:15:32", loomischaffee, "Atlanta", "Well", "Potable");
        WaterReport r2 = new WaterReport(jack, "04/29/1997 10:42:54", ocala, "Ocala", "Bottled", "Muddy");

        //Testing adding two separate Water Reports
        assertTrue(model.addWaterReport(r1));
        assertTrue(model.addWaterReport(r2));
        //Testing adding an identical water report
        assertFalse(model.addWaterReport(r1));
    }

    @Test
    //Rachel's JUnit
    public void getMonthSubmittedTest() {
        User leslie = new User("leslie", "waffles@pawnee.com", "knope123", "123456", "Manager");
        Location hongKong = new Location("hong kong");
        //Test for months Jan through Sept (1-9)
        PurityReport pr1 = new PurityReport(leslie, "04/04/2017", hongKong, "Treatable", 5, 5);
        //assertTrue(pr1.getMonthSubmitted() == 04);
        assertFalse(pr1.getMonthSubmitted() == 5);
        //Test for months Oct through Dec (10--12)
        PurityReport pr2 = new PurityReport(leslie, "10/04/2017", hongKong, "Treatable", 5, 5);
        assertTrue(pr2.getMonthSubmitted() == 10);
        assertFalse(pr2.getMonthSubmitted() == 1);
        //Test without slashes
        PurityReport pr3 = new PurityReport(leslie, "04042017", hongKong, "Treatable", 5, 5);
        assertTrue(pr3.getMonthSubmitted() == 4);
        //No need to test for invalid or null data, as the date is auto-generated
    }


}