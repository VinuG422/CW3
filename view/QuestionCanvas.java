package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;


public class QuestionCanvas extends JPanel {
    private int questionNumber;

    private QuestionPanel panel;
    private Collection<CanvasQuestionPanel<? extends Question>>canvasQuestionPanels;

    public QuestionCanvas(QuestionPanel panel, int questionNumber) {
        this.panel = panel;
        this.questionNumber = questionNumber;

		setPreferredSize(new Dimension(500, 400));
		GridLayout gridLayout = new GridLayout(questionNumber, 1);
		gridLayout.setVgap(30);
        setLayout(gridLayout);
        setBackground(Color.black);
    }

    /*
        This method removes current questions from canvas and fills canvas with new random
        questions from question bank
     */
    public void reset() {
        canvasQuestionPanels = new ArrayList<>();
        removeAll();

        // getting questionNumber questions from question bank
        Collection<Question> questions = panel.getQuestionBank().getRandomQuestions(questionNumber);
        // iterating over obtained questions to create a panel for each question
        for (Question question : questions) {
            CanvasQuestionPanel<? extends Question> canvasQuestionPanel;
            if (question instanceof NoChoiceQuestion) {
                // creating NoChoiceQuestionPanel for NoChoiceQuestion
                canvasQuestionPanel = new NoChoiceQuestionPanel((NoChoiceQuestion) question);
            }
            else if (question instanceof ChoiceQuestion) {
                // creating ChoiceQuestionPanel for ChoiceQuestion (including TrueFalseQuestion)
                canvasQuestionPanel = new ChoiceQuestionPanel((ChoiceQuestion) question);
            }
            else {
                // creating IntervalQuestionPanel for IntervalQuestion
                canvasQuestionPanel = new IntervalQuestionPanel((IntervalQuestion) question);
            }
            // adding panels to canvas
            add(canvasQuestionPanel);
            canvasQuestionPanels.add(canvasQuestionPanel);

            revalidate();
            repaint();
        }
    }

    /*
        Method for making check of user's guesses.
        Method calculates overall results, updates score message label, and highlights correct and incorrect answers
     */
    public int check() {
        int counter = 0;
        for (CanvasQuestionPanel<? extends Question> canvasQuestionPanel : canvasQuestionPanels) {
            if (canvasQuestionPanel.check()) {
                // highlight panel with green, if the answer is correct
                canvasQuestionPanel.setBackground(Color.GREEN);
                counter++;
            }
            else {
                // highlight panel with red, if the answer is incorrect
                canvasQuestionPanel.setBackground(Color.RED);
            }

        }
        return counter;
    }

    /*
        Abstract class representing panel with single question in the canvas
     */
    private static abstract class CanvasQuestionPanel<Q extends Question> extends JPanel {
        // Question panel stands for
        protected final Q question;
        // panel, containing answer input widgets
        protected final JPanel answersPanel;

        public CanvasQuestionPanel(Q question) {
            this.question = question;
            // setting size and layout
            setPreferredSize(new Dimension(400, 80));
            setLayout(new BorderLayout());

            // setting question text label
            JLabel label = new JLabel(question.getText());
            label.setHorizontalAlignment(JLabel.CENTER);
            add(BorderLayout.CENTER, label);

            // putting answerPanel to the south
            answersPanel = new JPanel();
            add(BorderLayout.SOUTH, answersPanel);
        }

        // each not-abstract children class must implement check method
        protected abstract boolean check();
    }

    /*
        Class, representing panel for NoChoice Question
     */
    private static class NoChoiceQuestionPanel extends CanvasQuestionPanel<NoChoiceQuestion> {
        private final JTextField answerField;

        public NoChoiceQuestionPanel(NoChoiceQuestion question) {
            super(question);
            // nochoice question has only text field as user asnwer widget
            answerField = new JTextField();
            answerField.setHorizontalAlignment(JTextField.CENTER);
            answersPanel.setLayout(new BorderLayout());
            answersPanel.add(BorderLayout.CENTER, answerField);
        }

        // to check correctness of answer, one just have to check test in textfield
        protected boolean check() {
            return question.check(answerField.getText());
        }
    }

    /*
        Class, representing panel for Choice Question
     */
    private static class ChoiceQuestionPanel extends CanvasQuestionPanel<ChoiceQuestion> {
        // array of radio buttons
        private final JRadioButton[] choicesButtons;

        // array of radio button labels
        private final String[] choices;

        public ChoiceQuestionPanel(ChoiceQuestion question) {
            super(question);
            Collection<String> choices = question.getChoices();
            // creating grid with choices.size() columns
            answersPanel.setLayout(new GridLayout(1, choices.size()));

            // creating array of radio buttons
            this.choicesButtons = new JRadioButton[choices.size()];
            // creating array of radio button labels
            this.choices = new String[choices.size()];

            int counter = 0;
            // creating common button group for all radio buttons
            ButtonGroup group = new ButtonGroup();
            for (String choice : choices) {
                // filling radio button and label elements
                this.choices[counter] = choice;
                JRadioButton radioButton = new JRadioButton(choice);
                this.choicesButtons[counter] = radioButton;
                // adding radio button to button group
                group.add(radioButton);
                // adding radio button to panel
                answersPanel.add(radioButton);
                counter++;
            }
        }

        // to check correctness of answer, one just have to check label of selected radio button
        protected boolean check() {
            for (int i = 0; i<choicesButtons.length; i++) {
                if (choicesButtons[i].isSelected()) {
                    // if selected button is found, return the result of question check
                    return question.check(choices[i]);
                }
            }
            // if no radio button was selected, return false
            return false;
        }
    }

    /*
        Class, representing panel for Interval Question
     */
    private static class IntervalQuestionPanel extends CanvasQuestionPanel<IntervalQuestion> {
        private JSlider slider;

        public IntervalQuestionPanel(IntervalQuestion question) {
            super(question);
            // creating slider widget with values from 0 to 100
            slider = new JSlider(JSlider.HORIZONTAL, 0, 100,50);
            slider.setMinimum(0);
            slider.setMaximum(100);
            slider.setMajorTickSpacing(10);
            slider.setMinorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setSize(400,100);

            // adding slider to answers panel
            answersPanel.setLayout(new BorderLayout());
            answersPanel.add(BorderLayout.CENTER, slider);
        }

        // to check answer, one has to pass string representation of slider value to question check function
        protected boolean check() {
            return question.check(Integer.valueOf(slider.getValue()).toString());
        }
    }
}
