package Peripherals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DBconnect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.EmailUtility;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	Connection conn = null;
    PreparedStatement pst = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RegisterGUI() {
		 
		conn = DatabaseConnection.getInstance().connect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		setBounds(100, 100, 658, 709);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 634, 134);
		panel.setBackground(new Color(255, 57, 57));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\verification.gif"));
		lblNewLabel.setBounds(49, 10, 130, 105);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Player Registration");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBackground(new Color(255, 57, 57));
		lblNewLabel_1.setBounds(274, 22, 324, 93);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UserName");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(106, 174, 103, 22);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2);
		
		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(106, 209, 448, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		

		JLabel lblNewLabel_2_1 = new JLabel("Email");
		lblNewLabel_2_1.setBounds(106, 263, 103, 22);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2_1);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(106, 298, 448, 30);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Password");
		lblNewLabel_2_1_1.setBounds(106, 360, 103, 22);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2_1_1);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(106, 392, 448, 30);
		contentPane.add(txtPassword);
		
		
		JPasswordField txtconfirmPassword = new JPasswordField();
		txtconfirmPassword.setBounds(106, 484, 448, 30);
		contentPane.add(txtconfirmPassword);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_2_1_1_1.setBounds(106, 452, 208, 22);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JLabel lblLoginFrame = new JLabel("Already Registered. Login!!!!");
		lblLoginFrame.setBounds(232, 524, 271, 22);
		lblLoginFrame.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblLoginFrame);
		
		JButton btnRegister = new JButton("Register\r\n");
		btnRegister.setBounds(106, 565, 448, 35);
		btnRegister.setBackground(new Color(255, 57, 57));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel\r\n");
		btnCancel.setBounds(106, 610, 448, 35);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCancel.setBackground(new Color(255, 57, 57));
		contentPane.add(btnCancel);
		
		JLabel backgroundImg = new JLabel("");
		backgroundImg.setIcon(new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\RegisterBackgrnd.png"));
		backgroundImg.setBounds(0, 132, 634, 520);
		contentPane.add(backgroundImg);
		
		
		lblLoginFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            	LoginGUI loginFrame = new LoginGUI();
            	loginFrame.setVisible(true);
                // Dispose of the current frame (LoginGUI)
                dispose();
            	  // Toggle the visibility of the login and registration panels
 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblLoginFrame.setForeground(Color.RED);
                lblLoginFrame.setText("<html><u>Already Registered. Login!!!!</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            	lblLoginFrame.setForeground(null);
            	lblLoginFrame.setText("Already Registered. Login!!!!");
            }
        });
		
		
		
		btnRegister.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String name = txtUsername.getText();
		        String email = txtEmail.getText();
		        char[] passwordChars = txtPassword.getPassword();
		        String password = new String(passwordChars);

		        char[] cnfmPassword = txtconfirmPassword.getPassword();
		        String confirmPassword = new String(cnfmPassword);
	

		        if (confirmPassword.equals(password)) { 
		            try {
		                if (isValidEmail(email)) {
		                	  String verificationCode = generateVerificationCode();

		                	EmailUtility.sendVerificationEmail(email, verificationCode);
		                	
		                	 // Prompt user to enter the verification code
		                	String enteredVerificationCode = JOptionPane.showInputDialog(
                                    null,
                                    "Enter the verification code sent to your email:",
                                    "Verification Code",
                                    JOptionPane.PLAIN_MESSAGE);

		                	 if (verificationCode.equals(enteredVerificationCode)) {
		                    String q = "INSERT INTO `users` (`userName`, `email`, `password`) VALUES ('"+name+"', '"+email+"', '"+password+"');";
		                    pst = conn.prepareStatement(q);
		                    pst.execute();
		                    JOptionPane.showMessageDialog(null, "You have been Successfully Registered");
		                    
		                    // Open the main GUI
		                    dispose();
                            MainGUI mainGUI = new MainGUI();
                            mainGUI.setVisible(true);
		                    
		                	 } else {
	                                JOptionPane.showMessageDialog(null, "Verification code is incorrect.");
	                            }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Enter an email in the correct format");
		                }
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Sorry unable to register!!");
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Passwords do not match");
		        }
		    }
		});

		
		 

		 
		 btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                txtUsername.setText("");
	                txtEmail.setText("");
	                txtPassword.setText("");
	                txtconfirmPassword.setText("");
	            }
	        });
		 
		 
		 //Hovering of Buttons
		 
		 btnRegister.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseEntered(MouseEvent e) {             
	        	 btnRegister.setBackground(new Color(204, 85, 0));
	         }

	         @Override
	         public void mouseExited(MouseEvent e) {
	        	 btnRegister.setBackground(new Color(255, 68, 68));
	         }
	     });
		   
		   btnCancel.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseEntered(MouseEvent e) {             
	        	 btnRegister.setBackground(new Color(204, 85, 0));
	         }

	         @Override
	         public void mouseExited(MouseEvent e) {
	        	 btnRegister.setBackground(new Color(255, 68, 68));
	         }
	     });
	}	

	private String generateVerificationCode() {
	    // Implement your logic to generate a random code (e.g., UUID)
	    return java.util.UUID.randomUUID().toString();
	}
	
	public boolean isValidEmail(String email) {
	    // Define a regular expression pattern for a valid email address
	    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

	    // Compile the pattern into a regular expression
	    Pattern pattern = Pattern.compile(emailPattern);

	    // Match the input email against the pattern
	    Matcher matcher = pattern.matcher(email);

	    // Return true if the email matches the pattern, indicating it's a valid email
	    return matcher.matches();
	}
}


