import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class Commands{

	//Stack
	
	//Output area for functions
    public JTextArea _output;
    
    //For reading through help.txt and history.txt
    public BufferedReader file;
    
    //Command lists
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
            System.err.println("IOException: " + e.getMessage());
        }  
	}
	
	public boolean isCustom(String command){
		return (customcmds.indexOf(command) != -1);
	}

    public void runCustom(String command){
        if (command.equals("help")) help();
        if (command.equals("history")) history();
    }
    
	public void help(){
	    //Displays all supported commands
	    for (String x: cmds){
		    _output.append(x);
		    _output.append("\n");
		}		
	}
	
	public void addToHistory(String command){
	    //Adds command to history.txt
	    try{
            String filename= "history.txt";
            //The true indicates data to be appended
            FileWriter fw = new FileWriter(filename,true); 
            fw.write(command);
            fw.write("\n");
            fw.close();
        }
        catch(IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }
        
    public void history(){
        //Read through history.txt
        try {
            file = new BufferedReader(new FileReader("history.txt"));
            String line;
            
            //Adds content of history.txt into history
            while ((line = file.readLine()) != null)
                for (String x: line.split("\n")){
                    history.add(x);
                }
            file.close();
        } 
        catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } 
        
        //Displays history 
	    for (String x: history){
		    _output.append(x);
		    _output.append("\n");
		}		
	}
}
