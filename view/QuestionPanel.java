package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.QuestionButtonListener;
import model.Question;
import model.QuestionBank;

public class QuestionPanel {
	// Question number for one round
	public static final int QUESTION_NUMBER = 4;
	private final JFrame window;

	private QuestionCanvas canvas;

	private JLabel messageLabel;
	private JButton newGameButton;
	private JButton checkAnswersButton;
	private QuestionBank questionBank;

	public QuestionPanel(JFrame window) {
		this.window = window;
	}

	public void init() {
		Container cp = window.getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel southPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1,4);
		gridLayout.setHgap(50);
		gridLayout.setVgap(10);
		southPanel.setLayout(gridLayout);
		cp.add(BorderLayout.SOUTH, southPanel);

		messageLabel = new JLabel();
		southPanel.add(messageLabel);

		checkAnswersButton = new JButton("Check answers");
		southPanel.add(checkAnswersButton);

		newGameButton = new JButton("New Game");
		newGameButton.setEnabled(false);
		southPanel.add(newGameButton);

		JButton exitButton = new JButton("Quit");
		exitButton.addActionListener(e -> System.exit(0));
		southPanel.add(exitButton);

		canvas = new QuestionCanvas(this, QUESTION_NUMBER);
		cp.add(BorderLayout.CENTER, canvas);

		questionBank = new QuestionBank();
		canvas.reset();

		QuestionButtonListener listener = new QuestionButtonListener(this);
		newGameButton.addActionListener(listener);
		checkAnswersButton.addActionListener(listener);
	}

	public JFrame getWindow(){
		return window;
	}

	public QuestionCanvas getCanvas(){
		return canvas;
	}

	public QuestionBank getQuestionBank() {
		return questionBank;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getCheckAnswersButton() {
		return checkAnswersButton;
	}
}


