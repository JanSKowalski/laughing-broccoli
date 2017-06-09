import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class PixelSorting{
  BufferedImage image;

  public static void main(String args[]) throws IOException{
    image = ImageIO.read(new File("image"));
    image.RedSort();
    image.GreenSort();
    image.BlueSort();

  }
  public static int[] quickSort(int[] arr, int low, int high){
    if (array == null || arr.length == 0) return;
    //pivot
    int mid = low + (high - low)/2;
    int piv = arr[mid];

    int i = low;
    int j = high;
    while (i < j){
      while (arr[i] < pivot) i++;
      while (arr[j] > pivot) j++;
      if(i <= j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        i++;
        j--;
      }
    }
    if (low < j) quickSort(arr, low, j);
    if (high > i) quickSort(arr, i, high);

    }


  public void RedSort(){
    int width = this.getWidth();
    int height = this.getHeight();
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
    int redPix[red.length - 1] = quickSort(red, 0, red.length - 1);
    int count = 0;
    for (int x = 0; x < width; x++){
      for (int y = 0; y < height; y++){
        values[count] = new Color(image.getRGB(x,y));
        image.setRGB(redPix[i], green[i], blue[i]);
        i++;
      }
    }
  }


    public void GreenSort(){
      int width = this.getWidth();
      int height = this.getHeight();
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
      int[] blue = new int[values.length];      for (int pix = 0; pix < totalPixels; pix++){
        Color c = values[i];
        green[pix] = c.getGreen();
        red[pix] = c.getRed();
        blue[pix] = c.getBlue();
      }
      int greenPix[green.length - 1] = quickSort(green, 0, green.length - 1);
      int count = 0;
      for (int x = 0; x < width; x++){
        for (int y = 0; y < height; y++){
          values[count] = new Color(image.getRGB(x,y));
          image.setRGB(red[i], greenPix[i], blue[i]);
          i++;
        }
      }
    }

    public void BlueSort(){
      int width = this.getWidth();
      int height = this.getHeight();
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
      int[] blue = new int[values.length];      for (int pix = 0; pix < totalPixels; pix++){
        Color c = values[i];
        blue[pix] = c.getBlue();
        green[pix] = c.getGreen();
        red[pix] = c.getRed();
      }
      int bluePix[blue.length-1] = quickSort(blue, 0, blue.length - 1);
      int count = 0;
      for (int x = 0; x < width; x++){
        for (int y = 0; y < height; y++){
          values[count] = new Color(image.getRGB(x,y));
          image.setRGB(red[i], green[i], bluePix[i]);
          i++;
        }
      }
    }
  }
}
