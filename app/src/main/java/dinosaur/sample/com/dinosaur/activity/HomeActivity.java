package dinosaur.sample.com.dinosaur.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.utils.voice.TTSListener;
import dinosaur.sample.com.dinosaur.utils.voice.TTSService;

public class HomeActivity extends AppCompatActivity implements TTSListener {

    private Button mQuizButton = null;
    private Button mLearnButton;
    private TTSService mService = null;
    private Context mContext;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;

        mQuizButton = (Button) findViewById(R.id.quizButton);
        mLearnButton = (Button) findViewById(R.id.learnButton);

        mService = new TTSService(this,this);

        mQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayInputNameAlert();

            }
        });


        mLearnButton.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, LearnActivity.class);
            startActivity(i);
        });
    }

    private void launchGameActivity() {

        if (mService != null) {
            mService.speak(getString(R.string.TTS_HOME_CLICK_MSG, mName), getString(R.string.TTS_UTTERANCE_HOME_QUIZCLICK_ID));
        }
        Intent i = new Intent(HomeActivity.this, GameActivity.class);
        i.putExtra(getString(R.string.NAME_CONSTANT),mName);
        startActivity(i);
    }


    private void displayInputNameAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        final EditText edittext = new EditText(mContext);
        builder.setMessage(getString(R.string.HOME_NAMEALERT_TITLE));
        builder.setView(edittext);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String inputName = edittext.getText().toString();
                if(inputName != null && "".equals(inputName)){
                    dialog.setMessage(getString(R.string.HOME_NAMEALERT_ERROR));

                }else {
                    mName = inputName;
                    dialog.dismiss();
                    launchGameActivity();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mService != null)
        {
            mService.stop();
        }
    }

    @Override
    public void init() {
        if(mService != null){
            mService.speak(getString(R.string.TTS_HOME_WELCOME_MSG),getString(R.string.TTS_UTTERANCE_HOME_WELCOMEMSG_ID));
        }
    }

    @Override
    public void start(String utteranceID) {

    }

    @Override
    public void end(String utteranceID) {
    }
}
