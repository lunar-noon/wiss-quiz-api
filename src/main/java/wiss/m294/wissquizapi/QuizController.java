package wiss.m294.wissquizapi;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wiss.m294.wissquizapi.model.Question;

@CrossOrigin
@RestController
public class QuizController {
	
	QuizService service = QuizService.getInstance();

	@GetMapping("/questions")
	public List<Question> all() {
		return service.getAll();
	}
	
	@PostMapping("/questions")
	public Question newQuestion(@RequestBody Question newQuestion) {
		return service.add(newQuestion);
	}
	
	@DeleteMapping("/questions/{id}")
	public void deleteQuestion(@PathVariable int id) {
		service.deleteById(id);
	}
}
