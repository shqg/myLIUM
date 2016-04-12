

import comirva.audio.util.MFCC;

import java.util.ArrayList;
import java.util.List;


public class MFCC_Coefficient {
	
	MFCC mfcc=new MFCC((float) 16000.0);
										//Creates a new MFCC object with default window size of 512 for the given sample rate. 
										//The overleap of the windows is fixed at 50 percent. 
										//The number of coefficients is set to 20 and the first coefficient is in use. 
										//The 40 mel-filters are place in the range from 20 to 16000 Hz.
	
	public List<double[]> mfcc_lists = new ArrayList<double[]>();
	
	public void mfcc(List<double[]> Frames_vor_Hamming){
		  List<double[]> Frames;
		  int p = 18;
//	        for (int i = 0; i < Frames_vor_Hamming.size(); i++) {
	            Frames = Frames_vor_Hamming;
	            double[] mfccCoeff = new double[p];// p = 20  512->20
	            List<double[]> mfccCoeff_list = new ArrayList<double[]>();
	            //jeder Frames hat ein mfccCoeff_list       
	            for (double[] Frame : Frames) {

	                mfccCoeff = mfcc.processWindow(Frame, 0);
	                mfccCoeff_list.add(mfccCoeff);
	            }


	            mfcc_lists =mfccCoeff_list;
	        }
//	}
	
	 
}
