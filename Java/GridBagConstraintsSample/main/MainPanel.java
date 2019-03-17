package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    public MainPanel() {
        init();
    }
    
    
    private void init() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JButton button1 = new JButton("Button 1");
        gbc.gridy = 0;
        this.add(button1, gbc);
        
        JButton button2 = new JButton("Button 2 - Slightly Longer");
        gbc.gridy = 1;
        this.add(button2, gbc);
        
        JButton button3 = new JButton("Button 3");
        gbc.gridy = 2;
        this.add(button3, gbc);
        
        JComboBox<String> cbox = new JComboBox<> (new String[] {"5", "4", "3333"}) ;
        gbc.gridy = 3;
        this.add(cbox, gbc);
        
        javax.swing.JTextField jtf = new javax.swing.JTextField();
        gbc.gridy = 4;
        GridBagConstraints gbc2 = (GridBagConstraints) gbc.clone();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        this.add(jtf, gbc2);
        
        JButton button4 = new JButton("Button 4");
        gbc.gridy = 5;
        this.add(button4, gbc);
    }
}
