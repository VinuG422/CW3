package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.QuestionBank;
import view.MenuScreen;
import view.QuestionPanel;

public class QuestionButtonListener implements ActionListener {

    private QuestionPanel panel;

    public QuestionButtonListener(QuestionPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        //System.out.println(button.getText());

        if (button == panel.getNewGameButton()) {
            // if newGame button was clicked
            panel.getCanvas().reset();
            // clearing message label
            panel.getMessageLabel().setText(" ");
            // deactivating new game button
            panel.getNewGameButton().setEnabled(false);
            // activating checkAnswers button
            panel.getCheckAnswersButton().setEnabled(true);
        }

        if (button == panel.getCheckAnswersButton()) {
            // if checkAnswers button was clicked
            int score = panel.getCanvas().check();
            // outputting score result to message label
            panel.getMessageLabel().setText("Your score is " + score + "/" + QuestionPanel.QUESTION_NUMBER);
            // activating new game button
            panel.getNewGameButton().setEnabled(true);
            // deactivating checkAnswers button
            panel.getCheckAnswersButton().setEnabled(false);
        }

//        if (button == panel.getQuitButton()) {
//            panel.getWindow().getContentPane().removeAll();
//            MenuScreen menu = new MenuScreen(panel.getWindow());
//            menu.init();
//            panel.getWindow().pack();
//            panel.getWindow().revalidate();
//        } else if (button == panel.getNextButton()) {
//            int index = panel.getCanvas().getQuestionIndex();
//            ++index;
//            if (index == QuestionBank.database.size()) {
//                index = 0;
//            }
//
//            panel.getCanvas().setQuestionIndex(index);
//            panel.getCanvas().repaint();
//        } else if (button == panel.getPrevButton()) {
//            int index = panel.getCanvas().getQuestionIndex();
//            --index;
//            if (index < 0) {
//                index = QuestionBank.database.size() - 1;
//            }
//
//            panel.getCanvas().setQuestionIndex(index);
//            panel.getCanvas().repaint();
//
//        }//else if(button==panel.getEnterButton())
//        {
//            int index = panel.getCanvas().getQuestionIndex();
//            if (index < 0) {
//                JOptionPane.showMessageDialog(panel.getWindow(), "Yes the anaswer is True or No, You are answer is false");
//                return;
//            }
//            String response = panel.getAnswerField().getText();
//            String message;
//            if (QuestionBank.database.get(index).checkAnswer(response)) {
//                message = response + ": Correct answer!";
//
//            } else {
//                message = response + ":Wrong answer!";
//
//            }
//
//            JOptionPane.showMessageDialog(panel.getWindow(), message);
//            panel.getAnswerField().setText("");
//        }
    }
}
	

