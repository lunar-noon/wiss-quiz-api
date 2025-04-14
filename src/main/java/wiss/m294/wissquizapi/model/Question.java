package wiss.m294.wissquizapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

	private int id;
	private String question;
	private List<String> answers;
	
	@JsonProperty("correct_answer")
	private String correctAnswer;

	public Question() {
	}

	public Question(String question, List<String> answers, String correctAnswer) {
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	public Question(int id, String question, List<String> answers, String correctAnswer) {
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
