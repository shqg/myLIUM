import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class AudioReader {
	
//	public List<String> list;
	String inputAudio="mixtest.wav";
    public List<double[]> alleDateienFrames;
    public  double MAX_16_BIT = Short.MAX_VALUE;
    
    public AudioReader()
	{		
//		this.list = new ArrayList<String>();
//		list.add("mixtest.wav"); // 0 Test1
		this.alleDateienFrames = new ArrayList<double[]>();
		this.audioreader();
	}
	
	public void audioreader(){
		
//		for(int i = 0; i <list.size(); i++){
			File fileIn=new File( inputAudio);
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
				//获得此音频输入流中声音数据的音频格式。每帧的大小，以字节为单位。
				int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
				int frameRate = java.lang.Math.round(audioInputStream.getFormat().getFrameRate());
				int numBytes = (int)audioInputStream.getFrameLength()* bytesPerFrame; 
   			
				AudioFormat audioFormat = audioInputStream.getFormat();
				int FrameSize = audioFormat.getFrameSize();
				// infos ausgeben zu einer audiofile (hz, bit, usw.)
				System.out.println("Gelesene Audiofile: "+fileIn);
				System.out.println("Format: "+audioInputStream.getFormat().toString());
//				System.out.println("Anzahl Bytes: "+numBytes);
//				System.out.println("Anzahl: "+audioInputStream.getFrameLength());
  			 		
   				byte[] audioBytes = new byte[numBytes];
  				int totalFramesRead = 0;
    			int numBytesRead = 0;
    			int numFramesRead = 0;
    			// Try to read numBytes bytes from the file.
    			while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
      				// Calculate the number of frames actually read.
      				//numFramesRead = numBytesRead / bytesPerFrame;
    				numFramesRead = numBytesRead /FrameSize;//
      				totalFramesRead += numFramesRead;
    			}	
//  			   System.out.println("Fertig.");	
  			   double[] byteTodouble = new double[totalFramesRead];//new   			  
  			   for (int t = 0; t < byteTodouble.length; t++) {               
                byteTodouble[t] = ((short) (((audioBytes[2*t+1] & 0xFF) << 8)
                		+ (audioBytes[2*t] & 0xFF))) / ((double) MAX_16_BIT);
  			   }
//  			   System.out.println("Anzahl double: " + byteTodouble.length);
  			   int N = 512;
  			   int M = 256;            
  			   List<double[]> Frames = new ArrayList<double[]>();
  			   for (int a = 0; a < byteTodouble.length - N; a += M) {
  				   double[] Frame = new double[N];
  				   for (int b = 0; b < N; b++) {
  					   Frame[b] = byteTodouble[a + b];
  				   }
  				   Frames.add(Frame);
  			   }
  			   System.out.println("Anzahl Frames: " + Frames.size());		   
  			   alleDateienFrames=Frames;
  			}catch (UnsupportedAudioFileException e) { 
  				// Handle the error...
  				e.printStackTrace();
				} catch (IOException e) {

	                e.printStackTrace();
	            }
//		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AudioReader ar=new AudioReader();
		HammingWindow hw = new HammingWindow();
		List<double[]> Frames_von_alleVedios = hw.hammingApply(ar.alleDateienFrames);
		  MFCC_Coefficient mc=new MFCC_Coefficient();
			 mc.mfcc(Frames_von_alleVedios);
			 //mfcc features mc.mfcc_lists
			 System.out.print(mc.mfcc_lists.get(3).length);
		//transfer to MFCC	Features 
			 
			 
			 
	}

}
