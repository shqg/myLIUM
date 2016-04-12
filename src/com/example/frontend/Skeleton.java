package com.example.frontend;
//imports for LIUM_SpkDiarization
import fr.lium.spkDiarization.lib.DiarizationException;
import fr.lium.spkDiarization.programs.MClust;
import fr.lium.spkDiarization.programs.MSeg;

public class Skeleton {
	// Audio recording + play back
    public AudioRecordWrapper recorderWrapper;
    public RawAudioPlayback audioPlayer;
    
//dialogs  对话框 不需要
    
    public static final String AUDIO_FILE = "/recordoutput.raw";
    public static final String CONFIG_FILE = "/config.xml";
    public static final String MFCC_FILE = "/test.mfc";
    public static final String UEM_FILE = "/test.uem.seg";   
    // Audio Settings  andriod media 主要播放功能 
 //   public static final int AUDIO_SOURCE = MediaRecorder.AudioSource.VOICE_RECOGNITION;
    public static final int AUDIO_SOURCE =1;
    public static final int SAMPLE_RATE = 16000;
    public static final int CHANNELS_IN = 1;
   public static final int CHANNELS_OUT = 1;
   public static final int AUDIO_FORMAT = 1;
   public static final int AUDIO_STREAM = 1;
   public static final int PLAYBACK_MODE = 1;
//    public static final int CHANNELS_IN = AudioFormat.CHANNEL_IN_MONO;
//    public static final int CHANNELS_OUT = AudioFormat.CHANNEL_CONFIGURATION_MONO;
//    public static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
//    public static final int AUDIO_STREAM = AudioManager.STREAM_MUSIC;
//    public static final int PLAYBACK_MODE = AudioTrack.MODE_STREAM;
    protected void onCreate(){
 	 new recordConvo() ; //audio input 
    	 new makeMFCC().execute();
    	 new diarize().execute();
    }
    
    private class recordConvo    {  	
		protected Void doInBackground(Void... params) {
			recorderWrapper = new AudioRecordWrapper(AUDIO_FILE, AUDIO_SOURCE, SAMPLE_RATE, CHANNELS_IN, AUDIO_FORMAT);
			recorderWrapper.record();
			return null;
		}
    }
    
    private class makeMFCC   {
//   		protected void onPreExecute() {
//			super.onPreExecute();
//			showDialog(MFCC_DIALOG);
//		}
		protected Void doInBackground(Void... params) {
			MfccMaker Mfcc = new MfccMaker(CONFIG_FILE, AUDIO_FILE, MFCC_FILE, UEM_FILE); 
			Mfcc.produceFeatures();
			return null;
		}
//
//		protected void onPostExecute(Void unused) {
//			dismissDialog(MFCC_DIALOG);
//		}
    }
    
    private class diarize   {
    	int DONE_LINEARSEG = 50;
    	int DONE_LINEARCLUST = 100;
    	String[] linearSegParams = {"--trace", "--help", "--kind=FULL", "--sMethod=GLR", "--fInputMask=/sdcard/test.mfc", "--fInputDesc=sphinx,1:1:0:0:0:0,13,0:0:0", "--sInputMask=/sdcard/test.uem.seg", "--sOutputMask=/sdcard/test.s.seg", "test"};
    	String[] linearClustParams = {"--trace", "--help", "--fInputMask=/sdcard/test.mfc", "--fInputDesc=sphinx,1:1:0:0:0:0,13,0:0:0", "--sInputMask=/sdcard/test.s.seg", "--sOutputMask=/sdcard/test.l.seg", "--cMethod=l", "--cThr=2", "test"};
 
//		protected void onPreExecute() {
//			super.onPreExecute();
//			showDialog(DRZ_DIALOG);
//		}
 
		protected Void doInBackground(Void... params) {
			try {
				MSeg.main(linearSegParams);
			} catch (DiarizationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			setProgress(DONE_LINEARSEG);


			try {
				MClust.main(linearClustParams);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			setProgress(DONE_LINEARCLUST);
			
			return null;
		}
 
//		protected void onPostExecute(Void unused) {
//			dismissDialog(DRZ_DIALOG);
//		}
 
//		protected void onProgressUpdate(Integer... value) {
//			dProgressDialog.setProgress(value[0]);
//		}
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
  ///////////////////////////////////////////  
 