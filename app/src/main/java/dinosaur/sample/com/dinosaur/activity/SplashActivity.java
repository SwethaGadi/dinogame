package dinosaur.sample.com.dinosaur.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.utils.voice.TTSListener;
import dinosaur.sample.com.dinosaur.utils.voice.TTSService;

public class SplashActivity extends AppCompatActivity implements TTSListener {

    private Context ctx = null;
    private TTSService mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ctx = this;
        mService = new TTSService(ctx,this);

    }

    public void launchHomeActivity(View view){
        Intent homeIntent = new Intent(ctx, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mService != null){
            mService.stop();
        }
    }

    @Override
    public void init() {
       if(mService != null){
           mService.speak(getString(R.string.TTS_SPLASH_WELCOME_MSG),getString(R.string.TTS_UTTERANCE_SPLASH_WELCOMEMSG_ID));
       }
    }

    @Override
    public void start(String utteranceID) {

    }

    @Override
    public void end(String utteranceID) {
       // TODO : disable the green button intially and enable it once the TTS is done speaking.
    }
}
