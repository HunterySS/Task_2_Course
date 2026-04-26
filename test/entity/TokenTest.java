package entity;

import com.ilya.textapp.entity.Token;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testTokenCreation() {
        Token token = new Token("Hello");
        assertEquals("Hello", token.getValue());
        assertEquals("Hello", token.restore());
    }

    @Test
    void testTokenCountLetters() {
        Token token = new Token("Hello123");
        assertEquals(5, token.countLetters());
        assertEquals(8, token.countSymbols());
    }

    @Test
    void testTokenIsLeaf() {
        Token token = new Token("Test");
        assertThrows(UnsupportedOperationException.class, () -> token.add(null));
        assertTrue(token.getChildren().isEmpty());
    }
}