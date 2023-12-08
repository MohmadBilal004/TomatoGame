package Peripherals;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Controller.LoginListener;
import Controller.SessionManager;
import DBconnect.DatabaseConnection;
import Models.User;

public class ScoreboardGUI extends JFrame {

    private JTable table;

    public ScoreboardGUI() {
        if (!SessionManager.isLoggedIn() || SessionManager.getCurrentUser() == null) {
            // User is not logged in, handle this case
            this.dispose();
            showLoginDialog();
            return;
        } else {
            showScoreBoard();
        }

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Bilal Akram\\eclipse-workspace\\TomatoGame\\images\\RegisterBackgrnd.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        add(backgroundLabel);

        List<String> names = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        List<Integer> levels = new ArrayList<>();
        List<String> ranks = new ArrayList<>();

        Connection conn = null;

        try {
            conn = DatabaseConnection.getInstance().connect();

            String query = "SELECT `userName`, `score`, `rank`, `emails` FROM `scoretbl` ORDER BY score DESC";

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            int rank = 1;
            while (rs.next()) {
                names.add(rs.getString("userName"));
                scores.add(rs.getInt("score"));
                levels.add(rs.getInt("rank"));
                if (rank == 1) {
                    ranks.add(rank + "st");
                    rank++;
                } else if (rank == 2) {
                    ranks.add(rank + "nd");
                    rank++;
                } else if (rank == 3) {
                    ranks.add(rank + "rd");
                    rank++;
                } else {
                    ranks.add(rank + "th");
                    rank++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        String[] columnNames = {"Rank", "Name", "Score", "Level"};
        Object[][] data = new Object[names.size()][4];

        for (int i = 0; i < names.size(); i++) {
            data[i][0] = ranks.get(i);
            data[i][1] = names.get(i);
            data[i][2] = scores.get(i);
            data[i][3] = levels.get(i);
        }

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        // Set cell renderer to center the values and increase the cell size
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Increase the row height
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(scrollPane, BorderLayout.CENTER);

        setTitle("Scoreboard");
        setSize(604, 698);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button click
                dispose(); 
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true); 
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        // Ensure the Z-order
        backgroundLabel.setComponentZOrder(buttonPanel, 0);
    }

    private void showLoginDialog() {
       
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        loginGUI.addLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                
                showScoreBoard();
                loginGUI.dispose(); 
                }

            @Override
            public void onLoginCancel() {
                
                loginGUI.dispose(); 
                dispose(); 
            }
        });
    }

    private void showScoreBoard() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScoreboardGUI());
    }
}
