package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    public MainFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Test");
        
        JPanel contentPane = new JPanel();
        // contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        this.setContentPane(contentPane);
        
        MainPanel mainPanel = new MainPanel();
        // this.add(mainPanel, BorderLayout.NORTH);
        // this.add(mainPanel);
        this.add(mainPanel, gbc);
        
        this.pack();
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
    // Set font for all components
    // Reference: https://stackoverflow.com/a/12730398
    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
    
    
    public static void main(String[] args) {
        MainFrame.setUIFont(new FontUIResource(new Font("Arial", Font.PLAIN, 16)));
        new MainFrame();
    }
}
