package dinosaur.sample.com.dinosaur;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import java.util.Locale;

import dinosaur.sample.com.dinosaur.utils.Utils;

public class TTSService extends UtteranceProgressListener implements TextToSpeech.OnInitListener{

    private TextToSpeech tTs = null;
    private Context mCtx = null;
    private TTSListener mListener = null;

    public TTSService(Context ctx,TTSListener listener)
    {
        mCtx = ctx;
        mListener = listener;
        tTs = new TextToSpeech(ctx, this);
    }

    public void speak(String message){
       speak(message,TextToSpeech.QUEUE_ADD,null,null);
    }

    public void speak(String message,String id)
    {
      speak(message,TextToSpeech.QUEUE_ADD,null,id);
    }

    public void speak(String message,int mode, String id)
    {
       speak(message,mode,null,id);
    }

    public void speak(String message, int mode, Bundle params, String id)
    {
      if(tTs != null){
          tTs.speak(message,mode,params,id);
      }else{
          Utils.LogOut(mCtx,mCtx.getString(R.string.LOG_TTS_NULL));
      }
    }


    @Override
    public void onStart(String utteranceId) {

        Utils.LogOut(mCtx,"calling onstart : "+utteranceId);
        mListener.start(utteranceId);
    }

    @Override
    public void onDone(String utteranceId) {

        Utils.LogOut(mCtx,"calling onDOne : "+utteranceId);
        mListener.end(utteranceId);
    }

    @Override
    public void onError(String utteranceId) {
       Utils.LogOut(mCtx,mCtx.getString(R.string.LOG_TTS_ERROR)+" : "+utteranceId);
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR){

            tTs.setLanguage(Locale.getDefault());
            tTs.setPitch(1.1f);
            tTs.setSpeechRate(0.9f);
            Utils.LogOut(mCtx,"calling init listener : "+mListener);
            tTs.setOnUtteranceProgressListener(this);
            mListener.init();

        }else
        {
           Utils.LogOut(mCtx,mCtx.getString(R.string.TTS_INIT_FAIL));
        }

    }

    public void stop()
    {
        if(tTs != null){
            tTs.stop();
            tTs.shutdown();
        }
        mCtx = null;
        mListener = null;
    }
}
