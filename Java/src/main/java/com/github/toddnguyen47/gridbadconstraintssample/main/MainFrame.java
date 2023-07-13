package com.github.toddnguyen47.gridbadconstraintssample.main;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

@SuppressWarnings("serial")
public class MainFrame extends javax.swing.JFrame {
  public MainFrame(JPanel mainPanel) {
    this.initComponents(mainPanel);
  }

  public void initComponents(JPanel mainPanel) {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("Test");

    JPanel contentPane2 = new JPanel();
    // Set Border
    contentPane2.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY, 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    // Set Layout
    contentPane2.setLayout(new BorderLayout());

    // Add the main panel to this content pane 2
    contentPane2.add(mainPanel, BorderLayout.CENTER);

    // +-----------------------------------+
    // | Do not change the following code! |
    // +-----------------------------------+

    // Make a ScrollPane
    javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(contentPane2);
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
    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
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

  /**
   * This function needs to be run BEFORE we make any components!
   */
  public static void setFontsAndLookAndFeel() {
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
    MainFrame.setUIFont(new FontUIResource(new Font(Font.SANS_SERIF, Font.PLAIN, 16)));
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(
        new Runnable() {

          @Override
          public void run() {
            // This function needs to be run BEFORE we make any components!
            MainFrame.setFontsAndLookAndFeel();

            // Now that we have set a look and feel, we can initialize components
            MainPanel panel = new MainPanel();
            new MainFrame(panel);
          }
        });
  }
}
