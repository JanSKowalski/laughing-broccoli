import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestFrame {

    public static void main(String args[]) {

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JScrollPane scroll = new JScrollPane(new TestPane());

        frame.add(scroll);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
