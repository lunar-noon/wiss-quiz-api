package wiss.m294.wissquizapi.model;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    void whenCreatingQuestion_thenFieldsAreSetCorrectly() {
        Question q = new Question("Was ist Java?", Arrays.asList("Sprache", "Insel", "Auto"), "Sprache");
        assertEquals("Was ist Java?", q.getQuestion());
        assertEquals("Sprache", q.getCorrectAnswer());
        assertEquals(3, q.getAnswers().size());
    }

    @Test
    void whenUsingDefaultConstructor_thenCanSetFields() {
        Question q = new Question();
        q.setQuestion("Testfrage?");
        q.setCorrectAnswer("Antwort A");
        q.setAnswers(Arrays.asList("A", "B", "C"));
        q.setId(42);

        assertEquals("Testfrage?", q.getQuestion());
        assertEquals("Antwort A", q.getCorrectAnswer());
        assertEquals(3, q.getAnswers().size());
        assertEquals(42, q.getId());
    }
}
