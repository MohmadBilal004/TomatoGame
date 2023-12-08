package Controller;
import Models.User; 



//Define the LoginListener interface
public interface LoginListener {
 void onLoginSuccess(User user);
 void onLoginCancel();
}