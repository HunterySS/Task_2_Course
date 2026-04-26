package entity;

import com.ilya.textapp.entity.Mark;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MarkTest {

    @Test
    void testMarkCreation() {
        Mark mark = new Mark(".");
        assertEquals(".", mark.restore());
    }

    @Test
    void testMarkCountLetters() {
        Mark mark = new Mark("!");
        assertEquals(0, mark.countLetters());
        assertEquals(1, mark.countSymbols());
    }

    @Test
    void testInvalidMark() {
        assertThrows(IllegalArgumentException.class, () -> new Mark("abc"));
        assertThrows(IllegalArgumentException.class, () -> new Mark("A"));
    }
}