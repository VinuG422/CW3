package model;

import java.util.*;
import java.util.function.Supplier;

public class QuestionBank {
    private final ArrayList<Question> database = new ArrayList<>();

    {
        // populating question base with some test questions
        database.add(new NoChoiceQuestion(
                "What is the capital city of Oklahoma",
                "Oklahoma City"
        ));

        database.add(new TrueFalseQuestion(
                "Is Barack Obama president of the US?",
                "false"
        ));

        Collection<String> choices = new ArrayList<>();
        choices.add("Spanish");
        choices.add("English");
        choices.add("Portuguese");
        database.add(new ChoiceQuestion(
                "What is the official language in Brazil?",
                "Portuguese",
                choices
        ));

        database.add(new IntervalQuestion(
                "How many states are there in the US?",
                50,
                3
        ));
    }

    /*
        Method for returning n random question (in random order), from database
        If database contains less questions, than n, all questions from database are returned
     */
    public Collection<Question> getRandomQuestions(int n) {
         List<Question> selected = new ArrayList<>(database);
         Collections.shuffle(selected);
         while (selected.size() > n){
                 Random random = new Random();
                 selected.remove(random.nextInt(selected.size()));
         }
         return selected;
    }
}
