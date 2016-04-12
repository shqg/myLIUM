
 
import java.util.List;

public class HammingWindow {

     int windowSize=512;
	 
	 public List<double[]> hammingApply(List<double[]> hammingdata) {
		 
//		 for (int i = 0; i < hammingdata.size(); i++) {
	            List<double[]> Frames = hammingdata;
	            double[] hw= hammingWindow(windowSize);
	            for (double[] Frame : Frames) {
	                for (int n = 0; n < windowSize; n++) {
	                    Frame[n] = Frame[n] * hw[n];

	                }
	            }
//	        }
	        return hammingdata;
	    }

	
	public double[] hammingWindow(int windowSize) {
		
		double[] hammingWindow = new double[windowSize]; 
		
	    double twopi = Math.PI * 2;
	    for (int i=0;i<windowSize;++i) {
	            hammingWindow[i] = 0.54 - 0.46*Math.cos(twopi*i/(windowSize-1));
	        }
		return hammingWindow;
	}

}
