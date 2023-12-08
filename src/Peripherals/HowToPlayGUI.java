package Peripherals;

import javax.swing.*;

import Controller.LoginListener;
import Controller.SessionManager;
import Models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HowToPlayGUI extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HowToPlayGUI frame = new HowToPlayGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HowToPlayGUI() {
    	
    	 if (!SessionManager.isLoggedIn() || SessionManager.getCurrentUser() == null) {
             // User is not logged in, handle this case
             this.dispose();
             showLoginDialog();
             return;
         } else {
        	 showHowToPlayGUI();
         }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("How to Play");
        setSize(1152, 648); // Set the size to 1152x648 pixels
        setResizable(false);
        setLocationRelativeTo(null);

        // Set up the background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\HowToPlayGUI.png");

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        // Adjusted positions and sizes based on the new size
        JLabel lblHowToPlay = new JLabel("How to Play");
        lblHowToPlay.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
        lblHowToPlay.setBounds(10, 11, 1116, 30); // Adjusted width to 1116
        contentPane.add(lblHowToPlay);


        // Adjusted positions and sizes based on the new size
        JLabel lblStep1 = new JLabel("1. Look at the image displayed on the screen.");
        lblStep1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep1.setBounds(334, 144, 603, 30); // Adjusted width to 1112
        contentPane.add(lblStep1);

        JLabel lblStep2 = new JLabel("2. Enter your guess for the correct number.");
        lblStep2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep2.setBounds(334, 193, 603, 30); // Adjusted width to 1112
        contentPane.add(lblStep2);

        JLabel lblStep3 = new JLabel("3. Click the 'OK' button to submit your answer.");
        lblStep3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep3.setBounds(334, 242, 603, 20); // Adjusted width to 1112
        contentPane.add(lblStep3);

        JLabel lblStep4 = new JLabel("4. If your answer is correct, your score will increase.");
        lblStep4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep4.setBounds(334, 285, 603, 30); // Adjusted width to 1112
        contentPane.add(lblStep4);

        JLabel lblStep5 = new JLabel("5. If your answer is wrong, you will lose a life.");
        lblStep5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep5.setBounds(334, 335, 603, 30); // Adjusted width to 1112
        contentPane.add(lblStep5);

        JLabel lblStep6 = new JLabel("6. Try to answer as many questions as possible within the time limit.");
        lblStep6.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblStep6.setBounds(334, 384, 603, 30); // Adjusted width to 1112
        contentPane.add(lblStep6);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainGUI frame = new MainGUI();
				frame.setVisible(true);
                dispose();             }
        });
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnClose.setBounds(543, 462, 119, 44); // Adjusted position and width
        contentPane.add(btnClose);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1152, 648); // Set the size to match the frame
                        contentPane.add(backgroundLabel);
    }
    
    
    private void showLoginDialog() {
        
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        loginGUI.addLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                
            	showHowToPlayGUI();
                loginGUI.dispose(); 
                }

            @Override
            public void onLoginCancel() {
                
                loginGUI.dispose(); 
                dispose(); 
            }
        });
    }

    private void showHowToPlayGUI() {
        this.setVisible(true);
    }
}
