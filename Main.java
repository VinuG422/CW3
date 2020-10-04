import javax.swing.*;

import view.MenuScreen;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocation(400, 100);
        window.setTitle("Welcome to the quiz");

        MenuScreen menu = new MenuScreen(window);
        menu.init();

        window.pack();
        window.setVisible(true);
    }
}





	
