package wiss.m294.wissquizapi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

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
		// Arrange

		// Act
		List<Question> result = service.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	void WithNewObject_add_returnsObjectWithId4() {
		// Arrange
		Question newQuestion = new Question("NewObject", Arrays.asList("1", "2", "3"), "1");

		// Act
		Question result = service.add(newQuestion);

		// Assert
		assertNotNull(result);
		assertEquals(4, result.getId());
	}

	@Test
	void WithId2_deleteById_removesSecondEntry() {
		// Arrange

		// Act
		service.deleteById(2);
		List<Question> result = service.getAll();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getId());
		assertEquals(3, result.get(1).getId());
	}
}
