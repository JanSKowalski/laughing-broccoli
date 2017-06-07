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
    
    public static JTextArea _output;
    public static JTextField _input;
    static JFrame _frame;
    static JPanel _panel;
        
    public void Terminal(){
    //Nothing needed
    }
    
    public static void createFrame(){
        _frame = new JFrame("Terminal");
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _panel = new JPanel();
        _panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
        _panel.setOpaque(true);
        
        //New object defining button action
        ButtonListener buttonListener = new ButtonListener();
                
        //Output Panel Specs       
        _output = new JTextArea(23, 55);
        _output.setWrapStyleWord(true);
        _output.setEditable(false);
        
        //Adds scrolling capability
        JScrollPane scroller = new JScrollPane(_output);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _panel.add(scroller);
        
        //Input Panel Specs
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        _input = new JTextField(20);
        _input.setActionCommand("Enter");
        _input.addActionListener(buttonListener);
        
        //Button Specs
        enterButton = new JButton("Enter");
        enterButton.setActionCommand("Enter");
        enterButton.addActionListener(buttonListener);
        enterButton.setEnabled(true);

        //Sets up Caret (The vertical line indicating active position)
        DefaultCaret caret = (DefaultCaret) _output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        //Finalizes Text-input panel
        inputpanel.add(_input);
        inputpanel.add(enterButton);
        _panel.add(inputpanel);
        
        //Adds panels to frame
        _frame.getContentPane().add(BorderLayout.CENTER, _panel);
        
        //Realize the components.
        _frame.pack();
        _frame.setLocationByPlatform(true);
        
        //Starts terminal center of screen
        _frame.setLocationRelativeTo(null);
        
        //Aesthetics 
        _frame.setVisible(true);
        _frame.setResizable(false);
        _input.requestFocus();
    }
    
    public static class ButtonListener implements ActionListener{
    
        public void actionPerformed(final ActionEvent ev){
        
		    //Object for custom commands
			Commands commands = new Commands(_output);

            //Trim input
            if (!_input.getText().trim().equals("")){
                String cmd = ev.getActionCommand();
                
                //If button is pressed
                if ("Enter".equals(cmd)){                
		            //Present input in scroll pane
                    _output.append(">> " + _input.getText());
                    _output.append("\n");

                    //Add command to history
                    commands.addToHistory(_input.getText());

			        //Check if command exists
		            if (commands.isCustom(_input.getText())){
			            commands.runCustom(_input.getText());
			        }
			        
		            else{
                        try{
			                //Attempt to run command through bash
                            BashInterface(_input.getText());
                            _output.append("\n");
                        } 
                        catch (Exception e) {
			                //Not a recognized command
                            _output.append(_input.getText() + ": command not found");
                            _output.append("\n");
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            //Clears input
            _input.setText("");
            
            //Brings this application to forefront of screen
            _input.requestFocus();
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
                    for (String x: line.split("\n"))
                        _output.append(x + "\n");
            }
            //Presents ErrorStream data
            while ((line = erreader.readLine()) != null) {
                    for (String x: line.split("\n"))
                        _output.append(x + "\n");
            }
        }
}
}
