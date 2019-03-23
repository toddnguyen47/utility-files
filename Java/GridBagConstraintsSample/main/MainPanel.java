package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


/**
 * This class will have a main panel with BorderLayout as its default.
 * A sub-panel will have a GridBagLayout, and this panel will be put into the
 * main panel's NORTH location.
 * Any sub-panels that need to expand will be placed into the main panel's
 * CENTER location.
 * @author Todd Nguyen
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    private JProgressBar pbar;
    private JTextArea textArea;
    private javax.swing.JTextField jtf; 
    private JButton btnStartProgress;
    private CustomTask m_customTask;
    
    public MainPanel() {
        init();
    }
    
    /** 
     * Make a subclass of SwingWorker.
     * Void - the result type returned by this SwingWorker's doInBackground and get methods
     * Integer - the type used for carrying out intermediate results by this SwingWorker's publish and process methods
     * @author Todd Nguyen
     *
     */
    private class CustomTask extends SwingWorker<Void, Integer> {

        /**
         * Main task. Execute in background
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            // Initialize progress. This is invoked using SwingWorker
            setProgress(0);
            while (progress < 100) {
                // Sleep for up to one second
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {};
                
                // Make random progress
                progress += random.nextInt(10);
                int tempProg = Math.min(progress,100);
                
                // So we don't have more than 100% completion
                setProgress(tempProg);
                
                // Send to SwingWorker's process() method using publish()
                publish(tempProg);
            }
            
            return null;
        }
        
        
        @Override
        public void process(java.util.List<Integer> integers) {
            int lastInt = integers.get(integers.size() - 1);
            String currentText = jtf.getText();
            currentText += "[" + lastInt + "] ";
            jtf.setText(currentText);
        }
        
        
        /**
         * Executed in the Event Dispatch Thread (EDT)
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            // Reenable the button
            btnStartProgress.setEnabled(true);
            
            // Reenable the cursor
            // setCursor(null);
            
            textArea.append("Done!\n");
        }
    };
    
    
    private void init() {
        this.setLayout(new BorderLayout());
        
        JPanel pagestartPanel = this.getPagestartPanel();
        this.add(pagestartPanel, BorderLayout.PAGE_START);
        
        JPanel centerPanel = this.getCenterPanel();
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
    
    private JPanel getPagestartPanel() {
        JPanel panelPagestart = new JPanel();
        int currentYCoord = 0;
        
        panelPagestart.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 0, 5, 0);
        
        btnStartProgress = new JButton("Start Progress Bar");
        btnStartProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartProgress.setEnabled(false);
                
                // Change cursor to waiting cursor
                // setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                // Instances of javax.swing.SwingWorker are not reusuable, so
                // we create new instances as needed.
                m_customTask = new CustomTask();
                m_customTask.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        customPropertyChange(evt);
                    }
                });
                // Schedule the task on a worker thread
                m_customTask.execute();
            }
        });

        gbc.gridy = currentYCoord++;
        panelPagestart.add(btnStartProgress, gbc);
        
        JButton button2 = new JButton("Button 2 - Slightly Longer");
        gbc.gridy = currentYCoord++;
        panelPagestart.add(button2, gbc);
        
        JButton button3 = new JButton("Button 3");
        gbc.gridy = currentYCoord++;
        panelPagestart.add(button3, gbc);
        
        JComboBox<String> cbox = new JComboBox<> (new String[] {"5", "4", "3333"}) ;
        gbc.gridy = currentYCoord++;
        panelPagestart.add(cbox, gbc);
        
        jtf = new javax.swing.JTextField();
        gbc.gridy = currentYCoord++;
        GridBagConstraints gbc2 = (GridBagConstraints) gbc.clone();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        panelPagestart.add(jtf, gbc2);
        
        JButton button4 = new JButton("Button 4");
        button4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You pressed button 4!");
            }
        });
        gbc.gridy = currentYCoord++;
        panelPagestart.add(button4, gbc);
        
        return panelPagestart;
    }
    
    
    private JPanel getCenterPanel() {
        int verticalGap = 5;
        
        JPanel centerPanel = new JPanel();
        
        // Set a top margin if needed
        centerPanel.setBorder(BorderFactory.createEmptyBorder(verticalGap, 0, 0, 0));
        
        // Use a BorderLayout as well to make it easy to expand
        centerPanel.setLayout(new BorderLayout(0, verticalGap));
        
        // Make a progress bar with 0 as min and 100 as max
        this.pbar = new JProgressBar(0, 100);
        this.pbar.setValue(0);
        this.pbar.setStringPainted(true);
        centerPanel.add(this.pbar, BorderLayout.PAGE_START);
        
        // Make a text area        
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        this.textArea.setLineWrap(true);
        
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(this.textArea);
        centerPanel.add(jsp, BorderLayout.CENTER);
        
        return centerPanel;
    }
    
    
    // Ref: https://docs.oracle.com/javase/tutorial/uiswing/components/progress.html
    // Invoked when a property changes
    public void customPropertyChange(java.beans.PropertyChangeEvent evt) {
        // Check if it is progress
        if (evt.getPropertyName().equalsIgnoreCase("progress")) {
            int value = (Integer) evt.getNewValue();
            this.pbar.setValue(value);
            // Return the progress bound property
            String s = MessageFormat.format("Completed {0}% of task\n", m_customTask.getProgress());
            this.textArea.append(s);
        }
    }
}
