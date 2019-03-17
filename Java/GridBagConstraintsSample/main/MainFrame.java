package main;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    public MainFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Test");
        
        JPanel contentPane = new JPanel();
        // Set Border
        contentPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Set Layout
        // contentPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));        
        contentPane.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        
        MainPanel mainPanel = new MainPanel();
        contentPane.add(mainPanel, gbc);

        
        // +-----------------------------------+
        // | Do not change the following code! |
        // +-----------------------------------+
        
        // Make a ScrollPane
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(contentPane);
        // Always show the scroll bar
        jsp.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Increase the mouse wheel scroll speed
        jsp.getVerticalScrollBar().setUnitIncrement(3);

        this.add(jsp);
        this.pack();
        this.setSize(400, 600);
        // Center the frame on screen
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
        // FIRST STEP: Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        // Set font size
        MainFrame.setUIFont(new FontUIResource(new Font("Arial", Font.PLAIN, 16)));
        new MainFrame();
    }
}
