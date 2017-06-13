import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Commands{

    //Stack for directory
    Stack<String> directory;
	
    //Output area for functions
    public JTextArea _output;
    
    //For reading through help.txt and history.txt
    public BufferedReader file;
    
    //Command lists
    public ArrayList<String> cmds;
    public ArrayList<String> customcmds;
    
    //History
    public ArrayList<String> history;
    
    //Determines whether class path is added to command
    boolean _pathactive;
    
    
    //Constructor Method
    public Commands(JTextArea output){
    //By default, -classpath is not automatically inserted into command
    _pathactive = false;
    
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
	
	//Checks to see if command is recognized
    public boolean isCustom(String command){
	return (customcmds.indexOf(command) != -1);
    }

    //Runs proper command
    public void runCustom(String command){
        if (command.equals("help")) help();
        if (command.equals("history")) history();
        if (command.equals("toggle")) toggle();
        if (command.equals("print")) print();
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

    public void setStack() throws Exception{
	//Initializes Stack
	directory = new NodeStack<String>();

	// Run command and wait till it's done
	Process p = Runtime.getRuntime().exec("pwd");
	p.waitFor();
            
	// Set up for info
	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        
	String line = "";
	String pwd = "";
	//Presents InputStream data
	while ((line = reader.readLine()) != null) {
		 pwd = line;
	}

	directory.push("/");
	for (String x: pwd.split("/"))
	    if (x != "")
	        directory.push(x);
    }

    public String printStack(){
	//Final string, describes current directory
	String currentdir = "";

	//Temporary stack to bounce through elements
	Stack<String> temp = new NodeStack<String>();
	temp.push("end");

	while (!directory.peek().equals("/"))
		temp.push(directory.pop());

	while (!temp.peek().equals("end")){
		currentdir += "/";
		currentdir += temp.peek();
		directory.push(temp.pop());
	}
	_output.append(currentdir);
	_output.append("\n");

	return currentdir;
	
    }
    
    //toggles the automatic addition of -classpath to commands, default false
    public void toggle(){
        _pathactive = false && _pathactive;
        if (_pathactive)
            _output.append("Commands will now have -classpath appended to them");
        else    
            _output.append("Commands will not have -classpath appended to them");
	    _output.append("\n");
	}
	
	public void print(){
	    _output.append(_pathactive + "\n");
	}
}
