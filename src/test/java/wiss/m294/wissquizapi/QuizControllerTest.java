package wiss.m294.wissquizapi;

import java.util.Arrays;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import wiss.m294.wissquizapi.model.Question;

public class QuizControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        QuizService service = QuizService.getInstance();

        if (service.getAll().size() < 3) {
            service.add(new Question("Frage 1", Arrays.asList("A", "B", "C"), "A"));
            service.add(new Question("Frage 2", Arrays.asList("A", "B", "C"), "B"));
            service.add(new Question("Frage 3", Arrays.asList("A", "B", "C"), "C"));
        }

        this.mockMvc = standaloneSetup(new QuizController()).build();
    }

    @Test
    void whenGetAll_thenStatus200AndReturnsJsonArray() throws Exception {
        mockMvc.perform(get("/questions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void whenPostValidQuestion_thenReturnsNewQuestionWithId() throws Exception {
        String newQuestionJson = """
            {
              "question": "Was ist die Hauptstadt von Frankreich?",
              "answers": ["Berlin", "Paris", "London"],
              "correct_answer": "Paris"
            }
        """;

        mockMvc.perform(post("/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newQuestionJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", greaterThan(0)))
            .andExpect(jsonPath("$.question", is("Was ist die Hauptstadt von Frankreich?")))
            .andExpect(jsonPath("$.correct_answer", is("Paris")));
    }
}
