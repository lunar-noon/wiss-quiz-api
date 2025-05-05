package wiss.m294.wissquizapi;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wiss.m294.wissquizapi.model.Question;

class QuizServiceJUnitTest {

    private QuizService quizService;

    @BeforeEach
    void setUp() {
        // Kein Singleton, um Tests unabhängig zu halten
        quizService = new QuizService();
    }

    @Test
    void shouldAddQuestionSuccessfully() {
        // Arrange
        Question newQuestion = new Question("Was ist 5 * 3?",
                Arrays.asList("15", "10", "20"), "15");

        // Act
        Question added = quizService.add(newQuestion);

        // Assert
        assertNotNull(added);
        assertEquals("Was ist 5 * 3?", added.getQuestion());
        assertEquals("15", added.getCorrectAnswer());
        assertTrue(added.getId() > 0);

        // Sicherstellen, dass sie auch in getAll enthalten ist
        assertTrue(quizService.getAll().contains(added));
    }
}
