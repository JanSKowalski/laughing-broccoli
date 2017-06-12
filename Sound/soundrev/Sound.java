package soundrev;
import java.io.*;
import java.lang.Math.*;

public class Sound
{
static int sensitivity = 800;
static int numFrames = 700000000;
    public static void main(String[] args)
    {
      try
	  {

	      if ( args.length > 2 ) sensitivity = Integer.parseInt(args[2]);
	      // Open the wav file specified as the first argument
	      WavFile inFile = WavFile.openWavFile(new File(args[0]));

	      // Display information about the wav file
	      inFile.display();

	      // Get the number of audio channels in the wav file
	      int numChannels = inFile.getNumChannels();
	      numFrames = (int) inFile.getNumFrames();
	      int sampleRate = (int) inFile.getSampleRate();
	      int validBits = inFile.getValidBits();
	      // Create a buffer to hold wav file in
	      double[] bufferin = new double[numFrames * numChannels];
	      // Read frames into buffer
	      inFile.readFrames(bufferin, numFrames);
	      // Close the inFile
	      inFile.close();
	      double[][] bufferout = new double[numChannels][numFrames];
	      inToOut(bufferin, bufferout);
		for (double[] channel : bufferout) {
			double[] chancopy = new double[channel.length];
			System.arraycopy( channel, 0, chancopy, 0, channel.length );
			double[] rev = reverseByMeasure(chancopy, sensitivity);
			for (int i = 0; i < channel.length; i++) channel[i] = channel[i] * 1.0 + rev[i];
		}
	      WavFile outFile = WavFile.newWavFile(new File(args[1]), numChannels, numFrames, validBits, sampleRate);
	      outFile.writeFrames(bufferout, numFrames);
	  }
      catch (Exception e)
	  {
	      e.printStackTrace();
	      System.out.println("Usage: Sound infile.wav outfile.wav [coarseness]");
	  }
    }
    public static void inToOut(double[] in, double[][] out){
	for (int chan = 0; chan < out.length; chan++){
	    for (int i = 0; i < out[0].length; i++) out[chan][i]=in[chan + i*(out.length)];
	}
    }
    public static double[] reverseByMeasure(double[] orig, int sensitivity){
	int[] beats = getBeats(orig, sensitivity);
	double[] ans = orig;
	for (int i = 0; i < beats.length - 1; i++) {
	    int start = beats[i];
	    int end = beats[i+1];
	    for (int j = 0; j < end - start; j++){
		ans[start + j] = orig[end - j];
	    }
	}
	return ans;
    }

    // Returns an array of indexes, where each index represents a high-energy onset.
    public static int[] getBeats(double[] orig, int sensitivity){
	int[] peaks = getPeaks(orig);
	int index = 0;
	int[] ans = new int[numFrames];
	int prevmax;
	boolean isBeat;
	for (int i = sensitivity; i < peaks.length - sensitivity; i++){
	    prevmax = i;
	    for (int j = i - sensitivity; j < i; j++){
		if (Math.abs(orig[peaks[j]]) > Math.abs(orig[peaks[prevmax]])) prevmax = j;
	    }
	    isBeat = true;
	    for (int j = i; j < i + sensitivity; j++){
		if (Math.abs(orig[peaks[j]]) > Math.abs(orig[peaks[prevmax]])) isBeat = false;
	    }
	    if (isBeat) {
		ans[index] = peaks[i];
		i += sensitivity;
		index++;
	    }
	}
	return ans;
    }
    
    // Returns an array of indexes, where each index represents a location in orig with the max value of one waveform crest/trough
    public static int[] getPeaks(double[] orig){
	int[] ans = new int[numFrames];
	int index = 0;
	int[] zeroes = getZeroes(orig);
	int max;
	for (int i = 0; i < zeroes.length - 1; i++){
	    max = zeroes[i];
	    for (int j = zeroes[i]; j < zeroes[i+1]; j++){
		if (Math.abs(orig[j]) > Math.abs(orig[max])) max = j;
	    }
	    ans[index] = max;
	    index++;
	}
	return ans;
    }
    

    public static int[] getZeroes(double[] orig){
	int[] ans = new int[numFrames];
	int index = 0;
	for (int i = 1; i < orig.length; i++){
	    if (orig[i]/orig[i - 1] <= 0) {
		ans[index] = i;
		index++;
	    }
	}
	return ans;
    }
}
    