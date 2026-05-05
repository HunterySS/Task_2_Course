package entity;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.TextLeaf;
import com.ilya.textapp.entity.impl.TextComponentType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

    @Test
    void testSentenceCreation() {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        sentence.add(new TextLeaf("Hello", TextComponentType.WORD));
        sentence.add(new TextLeaf(",", TextComponentType.PUNCTUATION));
        sentence.add(new TextLeaf("world", TextComponentType.WORD));
        sentence.add(new TextLeaf("!", TextComponentType.PUNCTUATION));

        assertEquals("Hello, world!", sentence.restore());
    }

    @Test
    void testParagraphCreation() {
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);

        TextComposite sentence1 = new TextComposite(TextComponentType.SENTENCE);
        sentence1.add(new TextLeaf("First", TextComponentType.WORD));
        sentence1.add(new TextLeaf(".", TextComponentType.PUNCTUATION));

        TextComposite sentence2 = new TextComposite(TextComponentType.SENTENCE);
        sentence2.add(new TextLeaf("Second", TextComponentType.WORD));
        sentence2.add(new TextLeaf(".", TextComponentType.PUNCTUATION));

        paragraph.add(sentence1);
        paragraph.add(sentence2);

        assertEquals("First. Second.", paragraph.restore());
    }

    @Test
    void testDocumentCreation() {
        TextComposite document = new TextComposite(TextComponentType.DOCUMENT);

        TextComposite paragraph1 = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite sentence1 = new TextComposite(TextComponentType.SENTENCE);
        sentence1.add(new TextLeaf("Hello", TextComponentType.WORD));
        paragraph1.add(sentence1);

        TextComposite paragraph2 = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite sentence2 = new TextComposite(TextComponentType.SENTENCE);
        sentence2.add(new TextLeaf("World", TextComponentType.WORD));
        paragraph2.add(sentence2);

        document.add(paragraph1);
        document.add(paragraph2);

        assertEquals("Hello\nWorld", document.restore());
    }

    @Test
    void testCountLetters() {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        sentence.add(new TextLeaf("Hello", TextComponentType.WORD));
        sentence.add(new TextLeaf("world", TextComponentType.WORD));

        assertEquals(10, sentence.countLetters());
    }
}