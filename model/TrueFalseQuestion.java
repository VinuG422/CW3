package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

/*
    Class, representing True/False questions
    It is Choice question with two choices: true and false
 */
public class TrueFalseQuestion extends ChoiceQuestion {
    public TrueFalseQuestion(String questionText, String answer) {
        super(questionText, answer, ((Supplier<Collection<String>>) () -> {
            Collection<String> choices1 = new ArrayList<>();
            choices1.add("true");
            choices1.add("false");
            return choices1;
        }).get());
    }

    @Override
    public String getText() {
        // adding suffix to question text
        return questionText + " True or false?";
    }
}
