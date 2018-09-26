package forneymonegerie;

/*
 * Name: ForneymonegerieTests
 * Author: Patrick Utz 
 * Date: 9/28/2018
 * Description: A junit class used to test the Forneymonegerie class
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ForneymonegerieTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Used as the basic empty menagerie to test; the @Before
    // method is run before every @Test
    Forneymonegerie fm;
    @Before
    public void init () {
        fm = new Forneymonegerie();
    }

    
    // =================================================
    // Unit Tests
    // =================================================
    
    @Test
    public void testEmpty() {
    	fm.collect("Dampymon"); // added tests
    	assertEquals(false, fm.empty());
    	Forneymonegerie fm2 = new Forneymonegerie();
    	assertEquals(true, fm2.empty());
    }
    
    @Test
    public void testSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
        fm.collect("Utzmon"); // added tests
        fm.collect("Utzmon");
        assertEquals(5, fm.size());
    }

    @Test
    public void testTypeSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(1, fm.typeSize());
        fm.collect("Burnymon");
        assertEquals(2, fm.typeSize());
        fm.collect("Dampymon"); // added tests
        fm.collect("Utzmon");
        fm.collect("Utzmon");
        assertEquals(3, fm.typeSize());
         
    }

    @Test
    public void testCollect() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.collect("Dampymon")); // added tests
        assertTrue(fm.collect("Utzmon"));
    }

    @Test
    public void testRelease() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        assertEquals(1, fm.typeSize());
        fm.release("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
        fm.collect("Dampymon"); // added tests
        assertEquals(true, fm.release("Dampymon"));
        assertEquals(true, fm.release("Dampymon"));
        assertEquals(false, fm.release("Dampymon"));
    }

    @Test
    public void testReleaseType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
        assertEquals(2, fm.typeSize());
        fm.releaseType("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
        fm.collect("Utzmon"); // added tests
        fm.releaseType("Utzmon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
    }

    @Test
    public void testCountType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(2, fm.countType("Dampymon"));
        assertEquals(1, fm.countType("Burnymon"));
        assertEquals(0, fm.countType("forneymon"));
        fm.collect("Utzmon"); // added tests
        fm.collect("Utzmon");
        fm.collect("Utzmon");
        assertEquals(3, fm.countType("Utzmon"));
        
    }

    @Test
    public void testContains() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("forneymon"));
        fm.collect("Utzmon"); // added tests
        assertTrue(fm.contains("Utzmon"));
        assertFalse(fm.contains("lalagaga"));
    }

    @Test
    public void testNth() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.collect("Dampymon");
        assertEquals("Dampymon", fm.nth(0));
        assertEquals("Dampymon", fm.nth(1));
        assertEquals("Burnymon", fm.nth(2));
        assertEquals("Zappymon", fm.nth(3));
        fm.collect("Zappymon"); // added tests
        fm.collect("Utzmon");
        assertEquals("Zappymon", fm.nth(4));
        assertEquals("Utzmon", fm.nth(5));
    }

    @Test
    public void testRarestType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
        fm.releaseType("Dampymon"); // added tests
        fm.releaseType("Zappymon");
        assertEquals(null, fm.rarestType());
    }

    @Test
    public void testClone() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        Forneymonegerie dolly = fm.clone();
        assertEquals(dolly.countType("Dampymon"), 2);
        assertEquals(dolly.countType("Burnymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
        fm.collect("Utzmon"); // added tests
        Forneymonegerie Patrick = fm.clone();
        assertTrue(fm.contains("Utzmon"));
        assertTrue(Patrick.contains("Utzmon"));
        fm.release("Utzmon");
        assertTrue(Patrick.contains("Utzmon"));
    }

    @Test
    public void testTrade() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Zappymon");
        fm2.collect("Leafymon");
        fm1.trade(fm2);
        assertTrue(fm1.contains("Zappymon"));
        assertTrue(fm1.contains("Leafymon"));
        assertTrue(fm2.contains("Dampymon"));
        assertTrue(fm2.contains("Burnymon"));
        assertFalse(fm1.contains("Dampymon"));
        Forneymonegerie patrick = new Forneymonegerie(); // added tests
        patrick.collect("Utzmon");
        patrick.trade(fm1);
        assertTrue(patrick.contains("Leafymon"));
        assertTrue(fm1.contains("Utzmon"));
    }
    
    @Test
    public void testToString() { // added tests
    	fm.collect("Utzmon");
    	fm.collect("Yoyomon");
    	fm.collect("Yoyomon");
    	assertEquals("[ \"Utzmon\": 1, \"Yoyomon\": 2 ]", fm.toString());
    }

    @Test
    public void testDiffMon() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Dampymon");
        fm2.collect("Zappymon");
        Forneymonegerie fm3 = Forneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
        fm1.collect("Utzmon"); // added tests
        fm1.collect("Utzmon");
        fm2.collect("Utzmon");
        Forneymonegerie fm4 = Forneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm4.countType("Dampymon"), 1);
        assertEquals(fm4.countType("Burnymon"), 1);
        assertEquals(fm4.countType("Utzmon"), 1);
        assertFalse(fm4.contains("Zappymon"));
        
    }

    @Test
    public void testSameForneymonegerie() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Burnymon");
        fm2.collect("Dampymon");
        fm2.collect("Dampymon");
        assertTrue(Forneymonegerie.sameCollection(fm1, fm2));
        assertTrue(Forneymonegerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(Forneymonegerie.sameCollection(fm1, fm2));
        fm1.collect("Utzmon"); // added tests
        fm2.collect("Patrickmon");
        assertFalse(Forneymonegerie.sameCollection(fm1, fm2));
        fm2.collect("Utzmon");
        fm1.collect("Patrickmon");
        fm1.collect("Leafymon");
        assertTrue(Forneymonegerie.sameCollection(fm1, fm2));
    }
}
