package entity;

import com.ilya.textapp.entity.TextLeaf;
import com.ilya.textapp.entity.impl.TextComponentType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextLeafTest {

    @Test
    void testWordCreation() {
        TextLeaf word = new TextLeaf("Hello", TextComponentType.WORD);
        assertEquals("Hello", word.getValue());
        assertEquals("Hello", word.restore());
        assertEquals(TextComponentType.WORD, word.getType());
    }

    @Test
    void testPunctuationCreation() {
        TextLeaf punct = new TextLeaf(".", TextComponentType.PUNCTUATION);
        assertEquals(".", punct.restore());
        assertEquals(TextComponentType.PUNCTUATION, punct.getType());
    }

    @Test
    void testWordCountLetters() {
        TextLeaf word = new TextLeaf("Hello123", TextComponentType.WORD);
        assertEquals(5, word.countLetters());
        assertEquals(8, word.countSymbols());
    }

    @Test
    void testPunctuationCountLetters() {
        TextLeaf punct = new TextLeaf("!", TextComponentType.PUNCTUATION);
        assertEquals(0, punct.countLetters());
        assertEquals(1, punct.countSymbols());
    }

    @Test
    void testLeafCannotAddChildren() {
        TextLeaf leaf = new TextLeaf("Test", TextComponentType.WORD);
        assertThrows(UnsupportedOperationException.class, () -> leaf.add(null));
        assertThrows(UnsupportedOperationException.class, () -> leaf.remove(null));
        assertTrue(leaf.getChildren().isEmpty());
    }
}