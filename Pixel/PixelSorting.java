import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class PixelSorting{

  public static void main(String args[]) throws IOException{
    BufferedImage image = ImageIO.read(new File("C:\\Users\\AnnaYang\\Desktop\\DSC_1452.JPG"));
    return RGBValues(image);

  }

  public static String RGBValues(BufferedImage image){
    int width = image.getWidth();
    int height = image.getHeight();
    int totalPixels = (width * height);
    Color[] values = new Color[totalPixels];
    int i = 0;
    for (int x = 0; x < width; x++){
      for (int y = 0; y < height; y++){
        values[i] = new Color(image.getRGB(x,y));
        i++;
      }
    }
    int[] red = new int[values.length];
    int[] green = new int[values.length];
    int[] blue = new int[values.length];
    for (int pix = 0; pix < totalPixels; pix++){
      Color c = values[i];
      red[pix] = c.getRed();
      green[pix] = c.getGreen();
      blue[pix] = c.getBlue();
    }
  }
}
