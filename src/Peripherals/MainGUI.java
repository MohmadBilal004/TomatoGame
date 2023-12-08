package Peripherals;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LoginListener;
import Controller.SessionManager;
import Models.User;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 1152, 648);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        ImageIcon gifIcon = new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\MainMenuImage.gif"); // Replace with the actual path to your GIF

	        // Ensure the content pane is transparent
	        contentPane.setOpaque(false);

	        // Set layout to null to position the background JLabel manually
	        contentPane.setLayout(null);
	        
	        JButton btnNewGame = new JButton("New Game");
	        btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 21));
	        btnNewGame.setBackground(new Color(27, 219, 228));
	        btnNewGame.setBounds(326, 303, 591, 37);
	        contentPane.add(btnNewGame);
	        
	        
	        
	        btnNewGame.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (SessionManager.isLoggedIn()) {
	                    dispose();
	                    GameGUI gameGUI = new GameGUI();
	                    gameGUI.setVisible(true);
	                } else {
	                    // User is not logged in, handle this case
	                    showLoginDialog();
	                }
	            }
	        });
	        
	        
	        JButton btnScores = new JButton("Scores");
	        btnScores.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (SessionManager.isLoggedIn()) {
	                    ScoreboardGUI ScoreFrame = new ScoreboardGUI();
	                    ScoreFrame.setVisible(true);
	                    dispose();
	                } else {
	                    // User is not logged in, handle this case
	                    showLoginDialog();
	                }
	            }
	        });
	        
	        btnScores.setFont(new Font("Tahoma", Font.BOLD, 21));
	        btnScores.setBackground(new Color(27, 219, 228));
	        btnScores.setBounds(326, 350, 591, 37);
	        contentPane.add(btnScores);
	        
	        JButton btnhowToPlay = new JButton("How To Play");
	        btnhowToPlay.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		HowToPlayGUI Frame = new HowToPlayGUI();
	        		Frame.setVisible(true);
                    dispose();
	        	}
	        });
	        btnhowToPlay.setFont(new Font("Tahoma", Font.BOLD, 21));
	        btnhowToPlay.setBackground(new Color(27, 219, 228));
	        btnhowToPlay.setBounds(326, 397, 591, 37);
	        contentPane.add(btnhowToPlay);
	        	        	        
	        	        	        JButton tnlogout = new JButton("Log Out");
	        	        	        tnlogout.setFont(new Font("Tahoma", Font.BOLD, 12));
	        	        	        tnlogout.setBackground(Color.RED);
	        	        	        tnlogout.setBounds(1009, 40, 85, 21);
	        	        	        contentPane.add(tnlogout);
	        	        	        
	        	        	        tnlogout.addActionListener(new ActionListener() {
	        	        	        	public void actionPerformed(ActionEvent e) {
	        	        	        		  // Log out the user
	        	        	                SessionManager.logout();

	        	        	                // Close the current frame (MainGUI)
	        	        	                dispose();

	        	        	                // Show the login dialog
	        	        	                showLoginDialog();
	        	        	        	}
	        	        	        });
	        	        	        
	        	        	        JButton btnexit = new JButton("Exit");
	        	        	        btnexit.setFont(new Font("Tahoma", Font.BOLD, 12));
	        	        	        btnexit.setBackground(Color.RED);
	        	        	        btnexit.setBounds(1009, 70, 85, 21);
	        	        	        contentPane.add(btnexit);
	        	        	        
	        	        	       
	        	        	        btnexit.addActionListener(new ActionListener() {
	        	        	        	public void actionPerformed(ActionEvent e) {
	        	        	        		dispose();
	        	        	        	}
	        	        	        });
	        	        	        
	        	        	        	        // Set the background GIF
	        	        	        	        JLabel background = new JLabel();
	        	        	        	        background.setIcon(gifIcon);
	        	        	        	        contentPane.add(background);
	        	        	        	        
	        	        	        	        	        // Position and size of the background JLabel
	        	        	        	        	        background.setBounds(0, 0, 1152, 648);
		//
	}
    private void showLoginDialog() {
        // Show a login dialog to the user
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        loginGUI.addLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                // Handle the login success, e.g., start the game
                showMainMenu();
                loginGUI.dispose(); // Close the login window
            }

            @Override
            public void onLoginCancel() {
                // Handle login cancellation
                loginGUI.dispose(); // Close the login window
                dispose(); // Close the game window
            }
        });
    }

    private void showMainMenu() {
        this.setVisible(true);
    }
}
