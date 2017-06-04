import javax.swing.UIManager;

public class Main{

    public static void main(String args[])    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        Terminal terminal = new Terminal();
        terminal.createFrame();
    }
}
