package game;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame {
    JButton backButton;

    //Constructor
    GameFrame() {
        this.setTitle("Snake");//Set the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close operation on exit
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        // Instantiate GamePanel
        GamePanel gamePanel = new GamePanel(); 
        
        backButton = new JButton("Go Back");// Back button initialization   
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();// Close current frame
                new GameMenu();
            }
        });

        this.add(gamePanel, BorderLayout.CENTER); // Add game panel to the center
        this.add(backButton, BorderLayout.SOUTH); // Add button to the SOUTH

        this.pack(); // Pack the components
        this.setVisible(true);
		this.setLocationRelativeTo(null);// Set frame location to the center of the screen
    }
}
