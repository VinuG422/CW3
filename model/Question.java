package model;

/*
	Abstract class, representing common question functionality
 */
public abstract class Question {
    protected String questionText;
    protected String answer;

    public Question(String questionText, String answer) {
    	this.questionText = questionText;
    	this.answer = answer;
    }

    /*
    	If answer is an integer, just convert it into string
     */
	public Question(String questionText, int answer) {
		this(questionText, Integer.valueOf(answer).toString());
	}

	/*
		By default, To check answer we compare user quess with correct answer ignore case
	 */
	public boolean check(String answer) {
		return answer.trim().equalsIgnoreCase(this.answer);
	}

    public abstract String getText();
}
