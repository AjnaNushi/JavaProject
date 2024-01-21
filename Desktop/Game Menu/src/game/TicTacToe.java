package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// implement the ActionListener interface
public class TicTacToe implements ActionListener{
	
	// Initialize necessary variables and components
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9]; 
	private boolean player1_turn;
	JButton restartButton;
	JButton backButton;
	private int player1Score = 0;
	private int player2Score = 0;
	
	// Create the constructor for Tic Tac Toe game
	TicTacToe(){
		
		// Set up the JFrame and its components
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(100, 10, 100));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Set up the title label
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(100, 50, 100));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		// Set layouts for panels
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		button_panel.setLayout(new GridLayout(3,3));
		
		// Create buttons for the game grid
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			// Set the background color of the buttons
			buttons[i].setBackground(new Color(203, 195, 227)); 
		}
		
		// Add components to the frame
		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		
		// Set up the game
		firstTurn();
		
		// Create the restart button
		restartButton = new JButton("Retry");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> restartGame());

        firstTurn();
        
        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current game window
                new GameMenu(); // Open the game menu
            }
        });

        // Add the restart and back buttons to a panel at the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(restartButton);
        bottomPanel.add(backButton);

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        firstTurn();	
	}

	// Action performed when a button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < 9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");
						player1_turn = false;
						textfield.setText("O turn");
						check();
						checkTie();
					}
				}
				else {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						player1_turn = true;
						textfield.setText("X turn");
						check();
						checkTie();
					}
				}
			}
		}
		
	}
	
	// Determine the first turn randomly
	public void firstTurn() {

		if(random.nextInt(2)==0) {
			player1_turn = true;
			textfield.setText("X turn");
		}
		else {
			player1_turn = false;
			textfield.setText("O turn");
		}
	}

	//Check for tie condition
	public void checkTie() {
	    boolean tie = true;

	    for (int i = 0; i < 9; i++) {
	        if (buttons[i].getText().equals("")) {
	            tie = false;
	            break;
	        }
	    }

	    if (tie) {
	        textfield.setText("It's a tie!");
	        for (int i = 0; i < 9; i++) {
	            buttons[i].setEnabled(false);
	        }
	    }
	}
	
	// Check for win conditions
	public void check() {
		//check X win condition
		if(
				(buttons[0].getText() == "X") && 
				(buttons[1].getText() == "X") &&
				(buttons[2].getText() == "X")
				) {
			xWins(0, 1, 2);
		}
		if(
				(buttons[3].getText() == "X") && 
				(buttons[4].getText() == "X") &&
				(buttons[5].getText() == "X")
				) {
			xWins(3, 4, 5);
		}
		if(
				(buttons[6].getText() == "X") && 
				(buttons[7].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(6, 7, 8);
		}
		if(
				(buttons[0].getText() == "X") && 
				(buttons[3].getText() == "X") &&
				(buttons[6].getText() == "X")
				) {
			xWins(0, 3, 6);
		}
		if(
				(buttons[1].getText() == "X") && 
				(buttons[4].getText() == "X") &&
				(buttons[7].getText() == "X")
				) {
			xWins(1, 4, 7);
		}
		if(
				(buttons[2].getText() == "X") && 
				(buttons[5].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(2, 5, 8);
		}
		if(
				(buttons[0].getText() == "X") && 
				(buttons[4].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(0, 4, 8);
		}
		if(
				(buttons[2].getText() == "X") && 
				(buttons[4].getText() == "X") &&
				(buttons[6].getText() == "X")
				) {
			xWins(4, 2, 6);
		}
		//check O win condition
		if(
				(buttons[0].getText() == "O") && 
				(buttons[1].getText() == "O") &&
				(buttons[2].getText() == "O")
				) {
			oWins(0, 1, 2);
		}
		if(
				(buttons[3].getText() == "O") && 
				(buttons[4].getText() == "O") &&
				(buttons[5].getText() == "O")
				) {
			oWins(3, 4, 5);
		}
		if(
				(buttons[6].getText() == "O") && 
				(buttons[7].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(6, 7, 8);
		}
		if(
				(buttons[0].getText() == "O") && 
				(buttons[3].getText() == "O") &&
				(buttons[6].getText() == "O")
				) {
			oWins(0, 3, 6);
		}
		if(
				(buttons[1].getText() == "O") && 
				(buttons[4].getText() == "O") &&
				(buttons[7].getText() == "O")
				) {
			oWins(1, 4, 7);
		}
		if(
				(buttons[2].getText() == "O") && 
				(buttons[5].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(2, 5, 8);
		}
		if(
				(buttons[0].getText() == "O") && 
				(buttons[4].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(0, 4, 8);
		}
		if(
				(buttons[2].getText() == "O") && 
				(buttons[4].getText() == "O") &&
				(buttons[6].getText() == "O")
				) {
			oWins(4, 2, 6);
		}
	}
	
	// Actions to take when X wins
	public void xWins(int a, int b, int c) {
		player1Score++;
	    updateScores();
		// Change button colors for winning combination
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		// Disable buttons and display winning message
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
	}
	
	// Actions to take when O wins
    public void oWins(int a, int b, int c) {
		player2Score++;
        updateScores();
		// Change button colors for winning combination
    	buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		// Disable buttons and display winning message
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
	}

	// Method to update and display scores
	public void updateScores() {
        textfield.setText("Player X: " + player1Score + "  Player O: " + player2Score);
    }
    
	// Method to restart the game
    private void restartGame() {
        // Reset button text, colors, and enable buttons
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
			// Set the background color of the buttons
			buttons[i].setBackground(new Color(203, 195, 227)); 
        }
        // Reset textfield and determine the first turn
        firstTurn();
        textfield.setText("Tic-Tac-Toe");
		updateScores();// Update scores after restarting the game
    } 
}
