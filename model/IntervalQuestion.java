package model;

/*
    Class, representing question with interval
 */
public class IntervalQuestion extends Question {
    private int diff;
    public IntervalQuestion(String questionText, int answer, int diff) {
        super(questionText, answer);
        this.diff = diff;
    }

    /*
        To check correctness of interval question guess, we calculcate the difference between
        quess and correct answer and compare it with diff
     */
    @Override
    public boolean check(String guess) {
        int intAnswer = Integer.parseInt(answer);
        int intGuess = Integer.parseInt(guess);
        return Math.abs(intAnswer - intGuess) <= diff;
    }

    @Override
    public String getText() {
        // adding question text suffix
        return questionText + " (+/-" + diff + ")";
    }
}
