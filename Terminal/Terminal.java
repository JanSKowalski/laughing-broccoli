import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.text.DefaultCaret;


public class Terminal{

    static JButton enterButton;
    
    public static JTextArea output;
    public static JTextField input;
    static JFrame frame;
    static JPanel panel;
        
    public void Terminal(){
    //Nothing needed
    }
    
    public static void createFrame(){
        frame = new JFrame("Terminal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        
        //New object defining button action
        ButtonListener buttonListener = new ButtonListener();
                
        //Output Panel Specs       
        output = new JTextArea(23, 55);
        output.setWrapStyleWord(true);
        output.setEditable(false);
        
        //Adds scrolling capability
        JScrollPane scroller = new JScrollPane(output);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroller);
        
        //Input Panel Specs
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        input = new JTextField(20);
        input.setActionCommand("Enter");
        input.addActionListener(buttonListener);
        
        //Button Specs
        enterButton = new JButton("Enter");
        enterButton.setActionCommand("Enter");
        enterButton.addActionListener(buttonListener);
        enterButton.setEnabled(true);

        //Sets up Caret (The vertical line indicating active position)
        DefaultCaret caret = (DefaultCaret) output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        //Finalizes Text-input panel
        inputpanel.add(input);
        inputpanel.add(enterButton);
        panel.add(inputpanel);
        
        //Adds panels to frame
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        
        //Realize the components.
        frame.pack();
        frame.setLocationByPlatform(true);
        
        //Starts terminal center of screen
        frame.setLocationRelativeTo(null);
        
        //Aesthetics 
        frame.setVisible(true);
        frame.setResizable(false);
        input.requestFocus();
    }
    
    public static class ButtonListener implements ActionListener{

        public void actionPerformed(final ActionEvent ev){
            if (!input.getText().trim().equals("")){
                String cmd = ev.getActionCommand();
                if ("Enter".equals(cmd)){
                    output.append(">> " + input.getText());
                    output.append("\n");
                    try{
                        BashInterface(input.getText());
                        output.append("\n");
                    } 
                    catch (Exception e) {
                        output.append(input.getText() + ": command not found");
                        output.append("\n");
                        e.printStackTrace();
                    }
                }
            }
            
            //Clears input
            input.setText("");
            
            //Brings this application to forefront of screen
            input.requestFocus();
        }
        
        public void BashInterface(String command) throws Exception{
            // Run command and wait till it's done
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            
            // Grab output for display
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader erreader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        
            String line = "";
            //Presents InputStream data
            while ((line = reader.readLine()) != null) {
                    output.append(line);
            }
            //Presents ErrorStream data
            while ((line = erreader.readLine()) != null) {
                    output.append(line);
            }
        }
}
}
