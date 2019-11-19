package dinosaur.sample.com.dinosaur.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import dinosaur.sample.com.dinosaur.data.Dinosaur;
import dinosaur.sample.com.dinosaur.data.GameState;
import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.utils.voice.TTSListener;
import dinosaur.sample.com.dinosaur.utils.voice.TTSService;
import dinosaur.sample.com.dinosaur.utils.Utils;


public class GameActivity extends AppCompatActivity implements TTSListener {

    private int mLevel = 1;

    private TextView mScoreVal = null;
    private TextView mQuestionView = null;
    private ImageButton mDino1, mDino2, mDino3,mDino4 = null;
    private String mQuestionString = null;
    private View mSelectedView = null;

    private Context mCtx = null;
    private TTSService mService = null;
    private GameState mGameState = null;
    private String mUserName;
    private String mReportTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mCtx = this;
        mScoreVal = (TextView) findViewById(R.id.score_value);
        mQuestionView = (TextView) findViewById(R.id.question_text);
        mDino1 = (ImageButton) findViewById(R.id.dino_image1);
        mDino2 = (ImageButton) findViewById(R.id.dino_image2);
        mDino3 = (ImageButton) findViewById(R.id.dino_image3);
        mDino4 = (ImageButton) findViewById(R.id.dino_image4);

        mUserName = getIntent().getStringExtra(getString(R.string.NAME_CONSTANT));

        mService = new TTSService(mCtx,this);
        mGameState = GameState.getInstance();

        loadValues();

    }

    private void loadValues()
    {
        updateScoreValue();

        List<Dinosaur> gameObjects = mGameState.getNextOptions();

        Utils.LogOut(mCtx,gameObjects.toString());
        mQuestionString = mGameState.getmCurrentIndex() + ") " + getString(R.string.dino_question) + " " + mGameState.getCurrentGameObject();

        mQuestionView.setText(mQuestionString);
        mDino1.setImageResource(gameObjects.get(0).getDrawableID());
        mDino1.setTag(gameObjects.get(0).getId());
        mDino2.setImageResource(gameObjects.get(1).getDrawableID());
        mDino2.setTag(gameObjects.get(1).getId());
        mDino3.setImageResource(gameObjects.get(2).getDrawableID());
        mDino3.setTag(gameObjects.get(2).getId());
        mDino4.setImageResource(gameObjects.get(3).getDrawableID());
        mDino4.setTag(gameObjects.get(3).getId());

        if(mGameState.getmCurrentIndex() >1){
            if(mService != null){
                mService.speak(mQuestionString, TextToSpeech.QUEUE_FLUSH,getString(R.string.TTS_UTTERANCE_QUESTION_ID));
            }
        }
    }

    private void updateScoreValue(){
        mScoreVal.setText(mGameState.getmCorrectCounter()+"/"+mGameState.getmTotalCount());
    }

    public void validateInput(View view)
    {
        int clickedView = (Integer) view.getTag();
        if(clickedView == mGameState.getmCurrentIndex()){
            if(mSelectedView == null) {
                mGameState.processCorrectAnswer();
            }
            view.setBackgroundColor(getColor(R.color.Green));
            if(mService != null){
                mService.speak(getString(R.string.TTS_UTTERANCE_CORRECT_RESP), TextToSpeech.QUEUE_ADD,getString(R.string.TTS_UTTERANCE_CORRECT_ID));
            }

        }else
        {
            view.setBackgroundColor(getColor(R.color.Red));
            if(mService != null){
                mService.speak(getString(R.string.TTS_UTTERANCE_WRONG_RESP), TextToSpeech.QUEUE_ADD,getString(R.string.TTS_UTTERANCE_WRONG_ID));
            }

        }
        mSelectedView = view;
//        continueQuiz();
    }

    private void continueQuiz() {
        mSelectedView = null;
        mGameState.continueQuiz();
        if(mGameState.getmCurrentIndex() > mGameState.getmTotalCount()){
            updateScoreValue();
            handleQuizDone();
        }else{
            loadValues();
        }
    }

    private void handleQuizDone(){
        String doneString;

        if(mService != null){
            if(mGameState.getmTotalCount() - mGameState.getmCorrectCounter() <= 1 ){
                doneString = getString(R.string.TTS_UTTERANCE_QUIZDONE_AMAZING,mUserName);
                mReportTitle = getString(R.string.GAME_ALERT_EXPERT);
            }else if(mGameState.getmTotalCount() - mGameState.getmCorrectCounter() <= mGameState.getmTotalCount()/3 ){
                doneString = getString(R.string.TTS_UTTERANCE_QUIZDONE_GREAT,mUserName);
                mReportTitle = getString(R.string.GAME_ALERT_GREAT);
            }else{
                doneString = getString(R.string.TTS_UTTERANCE_QUIZDONE_GOOD,mUserName);
                mReportTitle = getString(R.string.GAME_ALERT_GOOD);
            }
            displayFinalReport(doneString);
            mService.speak(doneString, TextToSpeech.QUEUE_FLUSH,getString(R.string.TTS_UTTERANCE_QUIZDONE_ID));
        }

    }

    private void displayFinalReport(String text){

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(getString(R.string.GAME_ALERT_TITLE));

        builder.setMessage(text);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mGameState = null;
                finish();
            }
        });
        builder.show();
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
            mService.speak(mQuestionString, TextToSpeech.QUEUE_FLUSH,getString(R.string.TTS_UTTERANCE_QUESTION_ID));
        }
    }

    @Override
    public void start(String utteranceID) {
    }

    @Override
    public void end(String utteranceID) {
        if(utteranceID.equals(getString(R.string.TTS_UTTERANCE_CORRECT_ID))){
            mSelectedView.setBackgroundColor(0x00000000);

            //Its running on a different thread, call continueQuiz on UI thread
            ((Activity)mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    continueQuiz();
                }
            });
        }else if(utteranceID.equals(getString(R.string.TTS_UTTERANCE_WRONG_ID))) {
            mSelectedView.setBackgroundColor(0x00000000);
        }else if(utteranceID.equals(getString(R.string.TTS_UTTERANCE_QUIZDONE_ID))){

        }

    }
}
