package wiss.m294.wissquizapi;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import wiss.m294.wissquizapi.model.Question;

@ExtendWith(MockitoExtension.class)
class QuizControllerMockTest {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    void whenMockedServiceReturnsQuestions_thenControllerReturnsThem() throws Exception {
        List<Question> mockQuestions = Arrays.asList(
            new Question("Frage A", Arrays.asList("A", "B", "C"), "A")
        );

        when(quizService.getAll()).thenReturn(mockQuestions);

        mockMvc.perform(get("/questions"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].question").value("Frage A"));

        verify(quizService).getAll();
    }
}
