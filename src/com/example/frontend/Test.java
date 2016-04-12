package com.example.frontend;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String configFile =" config.xml";
		 String  inputAudioFile ="mixtest.wav";
		 String outputMfccFile = "outputMfc";
		 String  outputUemFile = "UemFile";
		MfccMaker a=new MfccMaker(configFile,  inputAudioFile, outputMfccFile, outputUemFile);
 	   a.produceFeatures();
		
		 
		
	}

}
