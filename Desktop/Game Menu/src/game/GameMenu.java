package game;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GameMenu extends JFrame implements ActionListener {
    JButton snakeButton, ticTacToeButton;

    //Constructor
    GameMenu() {
        setTitle("Game Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Adjusted size to accommodate the large central text

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);  // Set background color to black
        // Creating buttons for Snake Game and Tic Tac Toe Game
        snakeButton = new JButton("Snake Game");
        ticTacToeButton = new JButton("Tic Tac Toe Game");

        // Adding action listeners to the buttons
        snakeButton.addActionListener(this);
        ticTacToeButton.addActionListener(this);

        // Setting fonts and colors for buttons
        snakeButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        ticTacToeButton.setFont(new Font("Arial", Font.BOLD, 24));

        snakeButton.setBackground(new Color(100, 180, 100));
        ticTacToeButton.setBackground(new Color(100, 100, 180));

        snakeButton.setForeground(Color.WHITE);
        ticTacToeButton.setForeground(Color.WHITE);

        panel.setLayout(new GridLayout(3, 1)); // Modified layout to include the large central text

        JLabel welcomeLabel = new JLabel("Welcome!\n\n Choose a game:", SwingConstants.CENTER);
        // Creating and styling a JLabel for the welcome text in the center
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.WHITE);

        panel.add(welcomeLabel);
        panel.add(snakeButton);
        panel.add(ticTacToeButton);
        
        add(panel);

        setVisible(true);
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == snakeButton) {
            // Launch Snake Game
            new GameFrame();
        } else if (e.getSource() == ticTacToeButton) {
            // Launch Tic Tac Toe Game
            new TicTacToe();
        }
    }

    // Main method to start the Game Menu
    public static void main(String[] args) {
        new GameMenu();
    }
}
