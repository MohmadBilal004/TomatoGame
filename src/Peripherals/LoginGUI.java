package Peripherals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LoginListener;
import Controller.SessionManager;
import DBconnect.DatabaseConnection;
import Models.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtpassword;
	
	
	Connection conn = null;
    PreparedStatement pst = null;
    
	
    private List<LoginListener> loginListeners = new ArrayList<>();


    public void addLoginListener(LoginListener listener) {
        loginListeners.add(listener);
    }

    // Call this method when the user logs in successfully
    private void notifyLoginSuccess(User user) {
        for (LoginListener listener : loginListeners) {
            listener.onLoginSuccess(user);
        }
    }

    // Call this method when the user cancels the login process
    private void notifyLoginCancel() {
        for (LoginListener listener : loginListeners) {
            listener.onLoginCancel();
        }
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginGUI() {
		conn = DatabaseConnection.getInstance().connect();
	    SessionManager.setConnection(conn); // Setting the database connection here

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 585);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 334, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\Untitleddesign.png"));
		lblNewLabel_2.setBounds(0, 0, 334, 551);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(360, 190, 103, 27);
		contentPane.add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setBounds(360, 227, 351, 28);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpassword.setBounds(360, 310, 351, 27);
		contentPane.add(txtpassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(360, 273, 103, 27);
		contentPane.add(lblPassword);
		
		JLabel Registerlbl = new JLabel("New to this Game? Register!!!!");
		Registerlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Registerlbl.setBounds(439, 347, 194, 27);
		contentPane.add(Registerlbl);
		
		JButton btnLogin = new JButton("Login\r\n");
		btnLogin.setBackground(new Color(255, 68, 68));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnLogin.setBounds(360, 421, 351, 35);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel\r\n");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnCancel.setBackground(new Color(255, 68, 68));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnCancel.setBounds(360, 466, 351, 35);
		contentPane.add(btnCancel);
		
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(new Color(185, 0, 0));
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		messageLabel.setBounds(360, 384, 351, 27);
		contentPane.add(messageLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\Lock.gif"));
		lblNewLabel_3.setBounds(458, 29, 149, 134);
		contentPane.add(lblNewLabel_3);
		
		
		 Registerlbl.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {        
	            	RegisterGUI registrationFrame = new RegisterGUI();
	                registrationFrame.setVisible(true);

	                // Dispose of the current frame (LoginGUI)
	                dispose();
	            	      
	            }
	            
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change the label's color or apply any other hover effect when the mouse enters
	                Registerlbl.setForeground(Color.RED);
	                Registerlbl.setText("<html><u>New to this Game? Register!!!!</u></html>");
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Reset the label's appearance when the mouse exits
	                Registerlbl.setForeground(null);
	                Registerlbl.setText("New to this Game? Register!!!!");
	            }
	        });
		 
		 
		 btnLogin.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        String email = txtEmail.getText();
			        char[] passwordChars = txtpassword.getPassword();
			        String password = new String(passwordChars);

			        if (SessionManager.login(email, password)) {
			        	
			        	 lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\UnLock.png"));
			        	 messageLabel.setText("Login successful");
			            JOptionPane.showMessageDialog(null, "Login successful");
			            
			            dispose(); // Close the login window
			            MainGUI mainGUIFrame = new MainGUI();
			            mainGUIFrame.setVisible(true);
			        } else {
			        	messageLabel.setText("Invalid email or password. Please try again.");
			            JOptionPane.showMessageDialog(null, "Invalid email or password");
			        }
			    }
			});
		/// Hovering of buttons
		 
		   btnLogin.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {             
	                btnLogin.setBackground(new Color(204, 85, 0));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                btnLogin.setBackground(new Color(255, 68, 68));
	            }
	        });
		   
		   
		   
		 
		 
		 btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                txtEmail.setText("");
	                txtpassword.setText("");	               
	            }
	        });
		 btnCancel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {             
	            	btnCancel.setBackground(new Color(204, 85, 0));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	btnCancel.setBackground(new Color(255, 68, 68));
	            }
	        });
		 
		 
		  


	}
}
