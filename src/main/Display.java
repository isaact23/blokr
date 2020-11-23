package main;

import java.awt.*;
import javax.swing.*;

/**
 * Display the game board and polyominos.
 */
public class Display {

    public Display() {

        // Create a JFrame window
        JFrame frame = new JFrame("blokr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a new label
        JLabel textLabel = new JLabel("Hello there", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        
        // Display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
