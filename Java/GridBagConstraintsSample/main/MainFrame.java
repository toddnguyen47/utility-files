package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    public MainFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Test");
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setContentPane(contentPane);
        
        MainPanel mainPanel = new MainPanel();
        // this.add(mainPanel, BorderLayout.NORTH);
        this.add(mainPanel);
        
        this.pack();
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        new MainFrame();
    }
}
