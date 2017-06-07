import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Commands{

	//Stack
	
	//Output area for functions
    public JTextArea _output;
    
    //For reading through help.txt and history.txt
    public BufferedReader file;
    
    //Command list
    public ArrayList<String> cmds;
    public ArrayList<String> customcmds;
    
    //History
    public ArrayList<String> history;
    
    //Constructor Method
	public Commands(JTextArea output){
	    //Sets output as Terminal textbox
	    _output = output;
	    
	    //Initializes ArrayLists
	    cmds = new ArrayList<String>();
	    customcmds = new ArrayList<String>();
	    history = new ArrayList<String>();
	    
	    //Automatically creates a list of functions upon creation
	    try {
            file = new BufferedReader(new FileReader("help.txt"));
            String line;
            
            //When reading through help.txt, this switch determines if
            //command is also placed in customcmds
            Boolean custom = true;
            
            //Adds content of help.txt into customcmds
            while ((line = file.readLine()) != null)
                for (String x: line.split("\n")){
                    if (x.indexOf("*") == -1){
                        cmds.add(x);
                        if (custom) 
                            customcmds.add(x);
                    }
                    else 
                        custom = false;
                }
            file.close();
        } 
        catch (IOException e) {
        }
        
	}
	
	public boolean isCustom(String command){
		return (customcmds.indexOf(command) != -1);
	}

    public void runCustom(String command){
        if (command.equals("help")) help();
    }
    
	public void help(){
	    for (String x: cmds){
		    _output.append(x);
		    _output.append("\n");
		}		
	}
}
