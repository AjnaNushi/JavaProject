package game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	// Constants for game dimensions, unit size, and other settings
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	private static final int UNIT_SIZE = 25;
	private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	private static final int DELAY = 150;
	// Arrays to hold x and y coordinates for snake body parts
	private final int x[] = new int[GAME_UNITS];
	private final int y[] = new int[GAME_UNITS];
	private int bodyParts = 3;
	private int applesEaten;
	private int applex;
	private int appley;
	private char direction = 'R';// Initial direction of the snake('R' -> right)
	private boolean running = false;
	Timer timer;
	Random random;
	
	//Constructor
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(20, 200, 100));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	// Method to start the game
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	// Override of JPanel's paintComponent method for drawing
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	// Method to draw the game elements
	public void draw(Graphics g) {
		// Drawing checkerboard background
		for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
			for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
				if ((i + j) % 2 == 0) {
					g.setColor(new Color(20, 200, 100)); // Color 1
				} else {
					g.setColor(new Color(30, 210, 120)); // Color 2
				}
				g.fillRect(i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			}
		}
	
		// Drawing game elements when the game is running
		if (running) {
			// Draw apple
			g.setColor(Color.red);
		    g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);
			// Draw snake
			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(new Color(10, 110, 200));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(20, 150, 230));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			// Display score
			g.setColor(Color.black);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
		}
		// Display game over screen when the game is not running
		else {
			gameOver(g);
		}
	}
	
	// Method to generate a new apple at a random location
	public void newApple() {
		boolean appleOnSnake = true;
		
		while (appleOnSnake) {
			applex = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
			appley = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	
			// Check if the new apple position is on the snake
			appleOnSnake = false;
			for (int i = 0; i < bodyParts; i++) {
				if (applex == x[i] && appley == y[i]) {
					appleOnSnake = true;
					break;
				}
			}
		}
	}
 
	// Method to move the snake
	public void move() {
		// Update coordinates of body parts based on the current direction
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x [i-1];
			y[i] = y [i-1];
		}
		// Move the head of the snake according to the current direction
		switch(direction) {
		case 'U'://Up
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D'://Down
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L'://left
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R'://Right
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	// Method to check if the snake has eaten the apple
	public void checkApple() {
		if((x[0] == applex) && (y[0] == appley)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	// Method to check for collisions
	public void checkCollisions() {
		// checks if head collides with body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
	
		// check if head touches left border
		if (x[0] < 0) {
			running = false;
		}
		// check if head touches right border
		if (x[0] > SCREEN_WIDTH - UNIT_SIZE) {
			running = false;
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if (y[0] > SCREEN_HEIGHT - UNIT_SIZE) {
			running = false;
		}
	
		if (!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		//Display score
		g.setColor(Color.black);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
	    FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
		//Display Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		//Display retry option
		g.setColor(Color.white);
	    g.setFont(new Font("Ink Free", Font.BOLD, 30));
	    FontMetrics metrics3 = getFontMetrics(g.getFont());
	    g.drawString("Press 'R' to retry", (SCREEN_WIDTH - metrics3.stringWidth("Press 'R' to retry")) / 2, SCREEN_HEIGHT / 2 + 100);
	}

	// ActionListener implementation for timer events
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	// KeyAdapter for handling keyboard input
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_R:
                if (!running) {
                    retry();
                }
                break;
			}
		}
	}
	
	//Retry method
	public void retry() {
	    bodyParts = 3;
	    applesEaten = 0;
	    direction = 'R';
	    running = false;
	    x[0] = SCREEN_WIDTH / 2;
	    y[0] = SCREEN_HEIGHT / 2;
	    newApple();
	    timer.restart();
	    running = true;
	    timer.start();
	}
}
