package entity;

import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.Mark;
import com.ilya.textapp.entity.Token;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClauseTest {

    @Test
    void testClauseAddAndRestore() {
        Clause clause = new Clause();
        clause.add(new Token("Hello"));
        clause.add(new Mark(","));
        clause.add(new Token("world"));

        assertEquals("Hello , world", clause.restore());
    }

    @Test
    void testClauseCountLetters() {
        Clause clause = new Clause();
        clause.add(new Token("Hello"));
        clause.add(new Token("world"));

        assertEquals(10, clause.countLetters());
    }
}