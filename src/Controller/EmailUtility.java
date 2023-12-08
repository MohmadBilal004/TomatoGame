package Controller;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtility {
	    private static final String USERNAME = "mohmadbilal004@gmail.com"; 
	    private static final String PASSWORD = "quff hlas pikx nuvc"; 

	    public static void sendVerificationEmail(String toEmail, String verificationCode) {
	    	
	    	//verificationCode = generateVerificationCode();
	    	
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(USERNAME, PASSWORD);
	            }
	        });

	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(USERNAME));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	            message.setSubject("Email Verification");
	            message.setText("Your verification code is: " + verificationCode);

	            Transport.send(message);

	            System.out.println("Verification email sent successfully!");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	            
	        }
	    }
	    
	    private static String generateVerificationCode() {
	        // Generate a random 4-character code including letters and numbers
	        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder code = new StringBuilder();
	        
	        for (int i = 0; i < 4; i++) {
	            int index = (int) (Math.random() * chars.length());
	            code.append(chars.charAt(index));
	        }

	        return code.toString();
	    }

	}



