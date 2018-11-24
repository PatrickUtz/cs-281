package dictreenary;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class DictreenaryTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Used as the basic empty Dictreenary to test; 
    // the @Before method is run before every @Test
    Dictreenary dt;
    Dictreenary dt2;
    Dictreenary dt3;
    @Before
    public void init () {
        dt = new Dictreenary();
        dt2 = new Dictreenary();
        dt3 = new Dictreenary();
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================
    
    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testDictreenary() {
        assertTrue(dt.isEmpty());
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testAddWord() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("bat");
    }

    @Test
    public void testHasWord() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("bat");
        dt.addWord("AcT");
        assertTrue(dt.hasWord("is"));
        assertTrue(dt.hasWord("it"));
        assertTrue(dt.hasWord("as"));
        assertTrue(dt.hasWord("ass"));
        assertTrue(dt.hasWord("at"));
        assertTrue(dt.hasWord("act"));
        assertTrue(dt.hasWord("bat"));
        assertFalse(dt.hasWord("ii"));
        assertFalse(dt.hasWord("i"));
        assertFalse(dt.hasWord("zoo"));
        
        dt2.addWord("it"); // added tests
        dt2.addWord("item");
        dt2.addWord("is");
        dt2.addWord("bakery");
        dt2.addWord("its");
        dt2.addWord("back");
        dt2.addWord("gone");
        assertTrue(dt2.hasWord("it"));
        assertTrue(dt2.hasWord("item"));
        assertTrue(dt2.hasWord("is"));
        assertTrue(dt2.hasWord("bakery"));
        assertTrue(dt2.hasWord("its"));
        assertTrue(dt2.hasWord("back"));
        assertTrue(dt2.hasWord("gone"));
        
        assertFalse(dt3.hasWord("Utz"));
    }

    @Test
    public void spellCheck() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("at");
        dt.addWord("item");
        dt.addWord("ass");
        dt.addWord("bat");
        dt.addWord("bother");
        dt.addWord("goat");
        dt.addWord("goad");
        assertEquals("is", dt.spellCheck("is"));
        assertEquals("it", dt.spellCheck("it"));
        assertEquals("item", dt.spellCheck("itme"));
        assertEquals("as", dt.spellCheck("as"));
        assertEquals("bat", dt.spellCheck("abt"));
        assertEquals("bother", dt.spellCheck("bohter"));
        assertEquals(null, dt.spellCheck("bad"));
        assertEquals(null, dt.spellCheck("zoo"));
    }
    
    @Test
    public void getSortedWords() {
        dt.addWord("is");
        dt.addWord("it");
        dt.addWord("as");
        dt.addWord("itenerary");
        dt.addWord("ass");
        dt.addWord("at");
        dt.addWord("zoo");
        dt.addWord("bat");
        dt.addWord("bother");
        ArrayList<String> solution = new ArrayList<String>(Arrays.asList(
            "as", "ass", "at", "bat", "bother", "is", "it", "itenerary", "zoo"
        ));
        assertEquals(solution, dt.getSortedWords());
    }
    
}
