package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class MenuScreen {
    private final JFrame window;

    public MenuScreen(JFrame window) {
        this.window = window;
    }

    public void init() {
		Container cp = window.getContentPane();
		JPanel menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(400, 200));
		menuPanel.setLayout(new GridLayout(1, 1));
		cp.add(BorderLayout.CENTER, menuPanel);

		JButton questionButton = new JButton("Welcome to the QUIZ");
		menuPanel.add(questionButton);

		cp.add(BorderLayout.CENTER, menuPanel);

		questionButton.addActionListener(event -> {
			window.getContentPane().removeAll();
			QuestionPanel panel = new QuestionPanel(window);
			panel.init();
			window.pack();
			window.revalidate();
		});
	}
}
