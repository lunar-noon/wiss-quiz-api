package wiss.m294.wissquizapi;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import wiss.m294.wissquizapi.model.Question;

@ExtendWith(MockitoExtension.class)
class QuizServiceSpyTest {

    @Spy
    QuizService quizService;

    @BeforeEach
    void setup() {
    }

    @Test
    void whenCallingAdd_thenSpyShouldRegisterCall() {
        Question question = new Question("Spy-Test", Arrays.asList("A", "B", "C"), "A");
        quizService.add(question);

        verify(quizService).add(question);
    }

    @Test
    void whenCallingDelete_thenVerifyMethodWasCalledWithRightId() {
        quizService.deleteById(2);

        verify(quizService).deleteById(2);
    }
}
