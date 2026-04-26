package service;

import com.ilya.textapp.entity.*;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.TokenSwapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenSwapperServiceTest {

    private TokenSwapperService service;

    @BeforeEach
    void setUp() {
        service = new TokenSwapperService();
    }

    @Test
    void testSwapFirstAndLastToken() throws TextProcessingException {
        DocumentRoot doc = new DocumentRoot();
        Block block = new Block();
        Clause clause = new Clause();

        clause.add(new Token("first"));
        clause.add(new Token("middle"));
        clause.add(new Token("last"));

        block.add(clause);
        doc.add(block);

        service.swapFirstAndLastToken(doc);

        String restored = clause.restore();
        assertTrue(restored.startsWith("last"));
        assertTrue(restored.contains("first"));
    }

    @Test
    void testNullDocumentThrowsException() {
        assertThrows(TextProcessingException.class, () -> service.swapFirstAndLastToken(null));
    }
}