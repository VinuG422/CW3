package model;

import java.util.ArrayList;
import java.util.Collection;
/*
    Class, representing question with choices
 */
public class ChoiceQuestion extends Question {
	private final Collection<String> choices;

	public ChoiceQuestion(String questionText, String answer, Collection<String> choices) {
		super(questionText, answer);
		this.choices = new ArrayList<>(choices);
		// check, that choices contain correct answers
		if (!choices.contains(answer)) {
			throw new IllegalArgumentException();
		}
	}

	public Collection<String> getChoices() {
		return choices;
	}

	@Override
	public String getText() {
		// adding suffix to question text
		return questionText + " Choose one of the options.";
	}
}
