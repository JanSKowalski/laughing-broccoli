import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class MyImage extends JPanel implements ActionListener{
  protected JButton r, g, b;
  BufferedImage image;
  File f;

  public MyImage(){
    ImageIcon red = new ImageIcon("red.png");
    ImageIcon green = new ImageIcon("green.jpg");
    ImageIcon blue = new ImageIcon("blue.png");

    r = new JButton("Sort by Reds", red);
    r.setVerticalTextPosition(AbstractButton.TOP);
    r.setHorizontalTextPosition(AbstractButton.LEADING);
    r.setMnemonic(KeyEvent.VK_R);
    r.setActionCommand("redSort");
    r.setToolTipText("Sorting by Red Pixels");

    g = new JButton("Sort by Greens", green);
    g.setVerticalTextPosition(AbstractButton.TOP);
    g.setHorizontalTextPosition(AbstractButton.CENTER);
    g.setMnemonic(KeyEvent.VK_G);
    g.setActionCommand("greenSort");
    g.setToolTipText("Sorting by Green Pixels");

    b = new JButton("Sort by Blues", blue);
    b.setVerticalTextPosition(AbstractButton.TOP);
    b.setHorizontalTextPosition(AbstractButton.CENTER);
    b.setMnemonic(KeyEvent.VK_B);
    b.setActionCommand("blueSort");
    b.setToolTipText("Sorting by Blue Pixels");

    r.addActionListener(this);
    g.addActionListener(this);
    b.addActionListener(this);
  }

  /*public void actionPerformed(ActionEvent e){
    if ("redSort".equals(e.getActionCammand())){

    }
  }*/
  public static void main(String[] args) {
         //Schedule a job for the event-dispatching thread:
         //creating and showing this application's GUI.
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 createFrame();
             }
         });
     }
 }
  public  void createFrame(){
    //creates frame
    JFrame frame = new JFrame("");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icon = new ImageIcon("C:\\Users\\AnnaYang\\Desktop\\a.jpg");
    JLabel label = new JLabel(icon);
    frame.add(label);
    MyImage newContentPane = new MyImage();
    frame.setContentPane(newContentPane);
    frame.pack();
  }

    //read image
  public void ReadImage(){
    try{
      image = ImageIO.read(new File("C:\\Users\\AnnaYang\\Desktop\\a.jpg"));//file path
    }catch(IOException e){
      System.out.println("Error: "+e);
    }
  }

    //write image
  public void WriteImage(){
    try{
      f = new File("C:\\Users\\AnnaYang\\Desktop\\a.jpg");  //output file path
      ImageIO.write(image, "jpg", f);
    }catch(IOException e){
      System.out.println("Error: "+e);
    }
  }





}//class ends here
