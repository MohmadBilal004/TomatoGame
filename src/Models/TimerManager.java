package Models;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TimerManager {
    private Timer timer;
    private int timeRemaining;

    public TimerManager(int initialTime, ActionListener actionListener) {
        this.timeRemaining = initialTime;
        this.timer = new Timer(1000, actionListener);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetTimer(int newTime) {
        timeRemaining = newTime;
        updateTimerLabel();
        timer.restart();
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    private void updateTimerLabel() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        // Assuming you have a label for displaying the time, update it here.
        // For example, you can call a method like updateTimeLabel(formattedTime);
    }

    public void resetTimerDefault() {
        resetTimer(60);
    }

    public void resetTimer_lvl_1() {
        resetTimer(45);
    }

    public void resetTimer_lvl_2() {
        resetTimer(30);
    }

    public void resetTimer_lvl_3() {
        resetTimer(15);
    }
}
