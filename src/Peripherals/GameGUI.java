package Peripherals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Controller.LoginListener;
import Controller.SessionManager;
import Engine.GameEngine;
import Models.User;
import java.awt.Font;
import javax.swing.JButton;

public class GameGUI extends JFrame {

	private JPanel contentPane;
    private JLabel questArea;
  

    private GameEngine myGame;
    private BufferedImage currentGame;
    private int score;
    private int level;
    
    private Timer timer;
    private int timeRemaining = 60; // 1 minute in seconds
    private JLabel lblTime;
    private JTextField txtScore;
  
    
    
    private int lives = 3;
    private JTextField txtLives;
	private JFrame loadingFrame;
	private JLabel loadingLabel;
	
	

	private int currentLevel = 0;
	private int correctAnswersForCurrentLevel = 0;
	private static final int MAX_CORRECT_ANSWERS_PER_LEVEL = 3;
	private static final int INITIAL_TIME_SECONDS = 60;
	private static final int TIME_REDUCTION_PER_LEVEL = 15;
	private JTextField txtlevels;
	private JTextField txtanswer;
	private JTextField txtresult;
   

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameGUI frame = new GameGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameGUI() {
       
    	if (!SessionManager.isLoggedIn() || SessionManager.getCurrentUser() == null) {
	        // User is not logged in, handle this case
			 	this.dispose();
		        showLoginDialog();
		        return;
	    } else {
	        // User is logged in, start the game
	        startGame();
	    }
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1152, 648);
        ImageIcon gifIcon = new ImageIcon(
                "C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\GameGUIMenu.gif"); 

        // Set the layout of the content pane to null to allow manual placement of
        
        contentPane = new JPanel(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        questArea = new JLabel();
        questArea.setForeground(new Color(0, 0, 0));
        questArea.setBounds(243, 131, 673, 429);
        contentPane.add(questArea);
        
        lblTime = new JLabel("01:00");
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTime.setBounds(60, 83, 97, 48);
        contentPane.add(lblTime);
                                
        txtLives = new JTextField();
        txtLives.setText("3");
        txtLives.setHorizontalAlignment(SwingConstants.CENTER);
        txtLives.setFont(new Font("Tahoma", Font.BOLD, 26));
        txtLives.setEnabled(false);
        txtLives.setEditable(false);
        txtLives.setDisabledTextColor(Color.BLACK);
        txtLives.setColumns(10);
        txtLives.setBorder(null);
        txtLives.setBackground(new Color(255, 255, 128));
       // txtLives.setBounds(106, 83, 96, 19);
        txtLives.setBounds(73, 213, 50, 48);
         contentPane.add(txtLives);
                                                                                
              txtScore = new JTextField();
              txtScore.setHorizontalAlignment(SwingConstants.CENTER);
              txtScore.setText("0");
              txtScore.setBackground(new Color(255, 255, 128));
              txtScore.setEnabled(false);
              txtScore.setEditable(false);
              txtScore.setFont(new Font("Tahoma", Font.BOLD, 40));
              txtScore.setBounds(980, 70, 79, 48);
              contentPane.add(txtScore);
              
              
              txtLives.setText("3");
              txtLives.setHorizontalAlignment(SwingConstants.CENTER);
              txtLives.setEnabled(false);
              txtLives.setEditable(false);
              txtLives.setColumns(10);
              txtLives.setBorder(null);
              
                                                                                                        
              txtlevels = new JTextField();
              txtlevels.setText("0");
              txtlevels.setHorizontalAlignment(SwingConstants.CENTER);
              txtlevels.setFont(new Font("Tahoma", Font.BOLD, 40));
              txtlevels.setEnabled(false);
              txtlevels.setEditable(false);
              txtlevels.setBackground(new Color(255, 255, 128));
              txtlevels.setBounds(1010, 200, 50, 41);
              contentPane.add(txtlevels);
               
               txtanswer = new JTextField();
               txtanswer.setFont(new Font("Tahoma", Font.PLAIN, 32));
               txtanswer.setBounds(460, 560, 110, 41);
               contentPane.add(txtanswer);
               txtanswer.setColumns(10);
               
               JButton btnOk = new JButton("OK !!\r\n");
               btnOk.setFont(new Font("Tahoma", Font.BOLD, 32));
               btnOk.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {
               		handleOkButtonClick();
               	}
               });
               btnOk.setBounds(590, 560, 118, 41);
               contentPane.add(btnOk);
               
               txtresult = new JTextField();
               txtresult.setToolTipText("");
               txtresult.setDisabledTextColor(new Color(0, 0, 0));
               txtresult.setHorizontalAlignment(SwingConstants.CENTER);
               txtresult.setBorder(null);
               txtresult.setFont(new Font("Tahoma", Font.PLAIN, 20));
               txtresult.setEnabled(false);
               txtresult.setEditable(false);
               txtresult.setVisible(false);
               txtresult.setText("Yeah You guessed the correct number");
               txtresult.setBounds(279, 92, 578, 35);
               contentPane.add(txtresult);
               txtresult.setColumns(10);
               
               JButton backButton = new JButton("Back");
               backButton.setBackground(new Color(255, 0, 0));
               backButton.setFont(new Font("Tahoma", Font.BOLD, 32));
               backButton.addActionListener(new ActionListener() {
               	public void actionPerformed(ActionEvent e) {    
               		exitGame();
               		
                       
               	}
               });
               backButton.setBounds(953, 560, 118, 41);
               contentPane.add(backButton);
                
              JLabel backgroundLabel = new JLabel();
              backgroundLabel.setIcon(gifIcon);
              
               backgroundLabel.setBounds(0, 0, getWidth(), getHeight());             
               contentPane.add(backgroundLabel);
               
               
        //startGame();
               
               myGame = new GameEngine(null);
               currentGame = myGame.nextGame();
               score = 0;
               level = 0;
               updateQuestArea();
               
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;

                if (timeRemaining >= 0) {
                    updateTimerLabel();
                } else {
                    timer.stop(); // Stop the timer when time's up
                    endGame();
                }
            }
        });
        timer.start();
    }
    
    private void handleOkButtonClick() {
        int userAnswer = Integer.parseInt(txtanswer.getText().trim());
       
        // Check if the user answer is correct
        boolean correct = myGame.checkSolution(userAnswer);

        if (correct) {
        	 score = myGame.getScore();
             txtresult.setVisible(true); // Show the result message
             txtresult.setText("Yeah, you guessed the correct number");
             txtScore.setText(String.valueOf(score));
             
            // Correct solution entered
            System.out.println("Correct solution entered!");
            if(currentLevel == 0) {
            	resetTimer();
            }else if(currentLevel == 1) {
            	resetTimer_lvl_1();
            }else if(currentLevel == 2) {
            	resetTimer_lvl_2();
            }else if(currentLevel == 3) {
            	resetTimer_lvl_3();
            }
            
            
            updateQuestArea();
         
            txtresult.setVisible(true);
            txtresult.setText("Yeah, you guessed the correct number");
            
            correctAnswersForCurrentLevel++;
            if (correctAnswersForCurrentLevel == MAX_CORRECT_ANSWERS_PER_LEVEL) {
                // User answered three questions correctly; level up
                currentLevel++;
                correctAnswersForCurrentLevel = 0;
                timeRemaining -= TIME_REDUCTION_PER_LEVEL;
            }
            txtlevels.setText(Integer.toString(currentLevel));
        } else {
           
            System.out.println("Incorrect solution entered!");
            txtresult.setVisible(true);
            txtresult.setText("Ooops!!! You have guessed the wrong number");
            lives--;

            // Update the lives label
            txtLives.setText(Integer.toString(lives));

            if (lives <= 0) {
                // No lives left; end the game
                endGame();
            }
        }

        
        txtanswer.setText("");
    }

    private void startGame() {
        if (questArea != null) {
            myGame = new GameEngine(null);
            currentGame = myGame.nextGame();
            score = 0;
            correctAnswersForCurrentLevel = 0;
            updateQuestArea();
            txtLives.setText(Integer.toString(lives));
            txtScore.setText(Integer.toString(score));
            txtlevels.setText(Integer.toString(currentLevel));
            
            timeRemaining = INITIAL_TIME_SECONDS;
            updateTimerLabel();
            timer.restart();
            
        }
    }

    private void updateQuestArea() {
        currentGame = myGame.nextGame();
        questArea.setIcon(new ImageIcon(currentGame));
    }

    // Rest of your code...
    
    
    private void updateTimerLabel() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String formattedTime = String.format("%02d:%02d", minutes, seconds);

        lblTime.setText(formattedTime);
    }
 
    private void resetTimer() {
	    timeRemaining = 60; // Reset the time to 1 minute (60 seconds)
	    updateTimerLabel();
	    timer.restart(); // Restart the timer
	}
 
 
    private void resetTimer_lvl_1() {
	    timeRemaining = 45; // Reset the time to 1 minute (60 seconds)
	    updateTimerLabel();
	    timer.restart(); // Restart the timer
	}
 
    private void resetTimer_lvl_2() {
	    timeRemaining = 30; // Reset the time to 30 seconds
	    updateTimerLabel();
	    timer.restart(); // Restart the timer
	}
    private void resetTimer_lvl_3() {
	    timeRemaining = 15; // Reset the time to 15 seconds
	    updateTimerLabel();
	    timer.restart(); // Restart the timer
	}
    
    private void endGame() {
        timer.stop(); // Stop the timer
        txtresult.setText("Out of lives! Game Over!");

        displayEndGameInfo();

      int option = JOptionPane.showConfirmDialog( this,"Do you wish to end the Game?\nClick No to Restart","Game Over",JOptionPane.YES_NO_OPTION );
      
        if (option == JOptionPane.YES_OPTION) {
            System.out.println("Game Over");

            // Get the current user information
            User currentUser = SessionManager.getCurrentUser();
            String username = currentUser.getUserName();
            String email = currentUser.getEmail();

            // Insert the score details into the database
            insertScoreToDatabase( email, score);

            // Close the current game GUI
            dispose();
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
        } else if (option == JOptionPane.NO_OPTION) {
            System.out.println("Restarting the Game");
            showLoadingScreen(); 
            resetGame();
          hideLoadingScreen();
        }
    }
    
    private void displayEndGameInfo() {
        // Get the current user information
        User currentUser = SessionManager.getCurrentUser();
        String username = currentUser.getUserName();
        String email = currentUser.getEmail();

        // Display the end game information
        JOptionPane.showMessageDialog(
                this,
                "Game Over!\n\n"
                        + "Username: " + username + "\n"
                        + "Email: " + email + "\n"
                        + "Level: " + currentLevel + "\n"
                        + "Score: " + score,
                "End Game Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void insertScoreToDatabase(String email, int score) {
        Connection conn = SessionManager.getConnection();
        PreparedStatement pst = null;

        try {
        	
        	String userName = SessionManager.getCurrentUser().getUserName();
        	
           
            String query = "INSERT INTO scoretbl ( userName,emails, score , rank) VALUES (?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, userName);
            pst.setString(2, email);
            pst.setInt(3, score);
            pst.setInt(4, currentLevel);
            pst.executeUpdate();

            System.out.println("Score details inserted into the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error inserting score details into the database.");
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void resetGame() {
        score = 0;
        level = 0;
        lives = 3; // Set the number of lives to the initial value
        txtScore.setText(String.valueOf(score));
        txtLives.setText(Integer.toString(lives));
        txtlevels.setText(Integer.toString(level));
        resetTimer();
        updateQuestArea();
    }
    
    private void showLoadingScreen() {
        loadingFrame = new JFrame("Loading...");
        loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setSize(300, 100);
        loadingFrame.setResizable(false);
        loadingFrame.setLocationRelativeTo(null);

        loadingLabel = new JLabel("Loading, please wait...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        loadingFrame.getContentPane().add(loadingLabel);
        loadingFrame.setVisible(true);
    }
    
    private void hideLoadingScreen() {
        if (loadingFrame != null) {
            loadingFrame.dispose();
        }
    }

    private void showLoginDialog() {
	    // Show a login dialog to the user
	    LoginGUI loginGUI = new LoginGUI();
	    loginGUI.setVisible(true);
	    loginGUI.addLoginListener(new LoginListener() {
	        @Override
	        public void onLoginSuccess(User user) {
	          
	            startGame();
	            loginGUI.dispose(); 
	        }

	        @Override
	        public void onLoginCancel() {
	          
	            loginGUI.dispose(); 
	            dispose();
	        }
	    });
	}
    private void exitGame() {
   	 int option = JOptionPane.showConfirmDialog( this,"You sure you want to exit the game?","All unsaved changes will not be saved",JOptionPane.YES_NO_OPTION );
        
   	 if(option == JOptionPane.YES_OPTION) {
   		 timer.stop();
   		 dispose();
            MainGUI mainGUI = new MainGUI();
            mainGUI.setVisible(true);
   	 }else if (option == JOptionPane.NO_OPTION) {
   		 
   	 }
   }
}
