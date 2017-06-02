import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextFieldInMenu {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                new TextFieldInMenu().go();
            }
        });
    }

    void go() {
        JFrame f = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("test");
        menu.add(new JMenuItem("A popup menu item"));
        menu.add(textfield);
        menu.add(new JMenuItem("Another popup menu item"));

	JMenu test = new JMenu("hello");
	mb.add(test);
	
        menu.addMenuListener(new MenuListener(){
            public void menuCanceled(MenuEvent e) {}
            public void menuDeselected(MenuEvent e) {}
            public void menuSelected(MenuEvent e) {
                EventQueue.invokeLater(new Runnable(){
                    public void run() {
                        textfield.grabFocus();
                    }
                });
            }
        });
        mb.add(menu); //adds menu option to bar
        f.setJMenuBar(mb); //adds mb as menu for frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    JTextField textfield = new JTextField();
}
