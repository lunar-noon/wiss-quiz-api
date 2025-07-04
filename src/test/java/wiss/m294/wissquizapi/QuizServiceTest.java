package wiss.m294.wissquizapi;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wiss.m294.wissquizapi.model.Question;

class QuizServiceTest {
	
	QuizService service;

	@BeforeEach
	void setup() {
		service = new QuizService();
	}



	@Test
	void getAll_returns3Questions() {

		List<Question> result = service.getAll();

		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	void WithNewObject_add_returnsObjectWithId4() {

		Question newQuestion = new Question("Was ist Java?", Arrays.asList("Sprache", "Insel", "Auto"), "Sprache");

		Question result = service.add(newQuestion);

		assertNotNull(result);
		assertEquals(4, result.getId());
	}

	@Test
	void WithId2_deleteById_removesSecondEntry() {

		service.deleteById(2);
		List<Question> result = service.getAll();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getId());
		assertEquals(3, result.get(1).getId());
	}

	@Test
	void whenDeletingNonExistentQuestion_thenNoChangeOccurs() {
		int initialSize = service.getAll().size();
		service.deleteById(999);
		assertEquals(initialSize, service.getAll().size());
	}
}
