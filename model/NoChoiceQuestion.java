package model;

public class NoChoiceQuestion extends Question {
    public NoChoiceQuestion(String questionText, String answer) {
        super(questionText, answer);
    }

    @Override
    public String getText() {
        return questionText;
    }
}
