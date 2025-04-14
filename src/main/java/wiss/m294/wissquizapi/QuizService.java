package wiss.m294.wissquizapi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import wiss.m294.wissquizapi.model.Question;

/**
 * This class exposes methods for adding, removing and loading {@link Question}
 * objects. {@link Question} objects are stored in a HashMap with an integer ID
 * as key, which is also available in the {@link Question} objects.
 * 
 * @author Patrick Meier
 *
 */
public class QuizService {

	private int idCounter;
	private HashMap<Integer, Question> questions;

	private static QuizService singleton;

	public QuizService() {
		questions = new HashMap<>();
		questions.put(1, new Question(1, "Der erste Begriff im Buchstabier-Alphabet ist...",
				Arrays.asList("Anton", "Alfa", "Anne"), "Alfa"));

		questions.put(2, new Question(2, "Der zweite Begriff im Buchstabier-Alphabet ist...",
				Arrays.asList("Butter", "Beta", "Bravo"), "Bravo"));

		questions.put(3, new Question(3, "Der dritte Begriff im Buchstabier-Alphabet ist...",
				Arrays.asList("Caesar", "Charlie", "Culprit"), "Charlie"));

		idCounter = 4;
	}

	/**
	 * This method collects all values of the HashMap and returns them as a List of
	 * Question objects.
	 * 
	 * @return List of Question objects
	 */
	public List<Question> getAll() {
		return questions.values().stream().toList();
	}

	/**
	 * This method sets the currently stored id-index to the provided newQuestion
	 * object, adds newQuestion to the HashMap, updates the id-index and finally
	 * returns the updated newQuestion object.
	 * 
	 * @param newQuestion {@link Question} object to be added to the HashMap
	 * @return {@link Question} object with an update {@link Question#id} value
	 */
	public Question add(Question newQuestion) {
		newQuestion.setId(idCounter);
		questions.put(idCounter, newQuestion);
		idCounter++;
		return newQuestion;
	}

	/**
	 * Removes a {@link Question} object from the HashMap via key value.
	 * 
	 * @param id key value used to remove the {@link Question} object from the
	 *           HashMap.
	 */
	public void deleteById(int id) {
		questions.remove(id);
	}

	/**
	 * Returns a singleton instance of the QuizService. Should be used in a server
	 * environment to ensure proper handling of HashMap data.
	 * 
	 * @return Singleton {@link QuizService} object
	 */
	public static QuizService getInstance() {
		if (singleton == null) {
			singleton = new QuizService();
		}

		return singleton;
	}
}
