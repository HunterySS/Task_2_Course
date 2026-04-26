package service;

import com.ilya.textapp.entity.*;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerServiceTest {

    private TextAnalyzerService service;
    private DocumentRoot document;

    @BeforeEach
    void setUp() {
        service = new TextAnalyzerService();
        document = new DocumentRoot();

        Block block = new Block();
        Clause clause1 = new Clause();
        clause1.add(new Token("Hello"));
        clause1.add(new Token("world"));
        block.add(clause1);

        Clause clause2 = new Clause();
        clause2.add(new Token("Hello"));
        clause2.add(new Token("again"));
        block.add(clause2);

        document.add(block);
    }

    @Test
    void testFindMaxSentencesWithSameWords() throws TextProcessingException {
        int result = service.findMaxSentencesWithSameWords(document);
        assertEquals(2, result);
    }

    @Test
    void testNullDocumentThrowsException() {
        assertThrows(TextProcessingException.class, () -> service.findMaxSentencesWithSameWords(null));
    }
}