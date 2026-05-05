package service;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.TextLeaf;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.ClauseSorterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ClauseSorterServiceTest {

    private ClauseSorterService service;
    private TextComponent document;

    @BeforeEach
    void setUp() {
        service = new ClauseSorterService();
        document = new TextComposite(TextComponentType.DOCUMENT);

        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);

        TextComposite sentence1 = new TextComposite(TextComponentType.SENTENCE);
        sentence1.add(new TextLeaf("bee", TextComponentType.WORD));

        TextComposite sentence2 = new TextComposite(TextComponentType.SENTENCE);
        sentence2.add(new TextLeaf("e", TextComponentType.WORD));

        TextComposite sentence3 = new TextComposite(TextComponentType.SENTENCE);
        sentence3.add(new TextLeaf("elephant", TextComponentType.WORD));

        paragraph.add(sentence1);
        paragraph.add(sentence2);
        paragraph.add(sentence3);
        document.add(paragraph);
    }

    @Test
    void testSortClausesByLetterCount() throws TextProcessingException {
        List<TextComponent> sorted = service.sortClausesByLetterCount(document, 'e');

        assertEquals(3, sorted.size());
        assertEquals("e", sorted.get(0).restore());
        assertEquals("bee", sorted.get(1).restore());
        assertEquals("elephant", sorted.get(2).restore());
    }
}