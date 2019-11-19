package dinosaur.sample.com.dinosaur.data;

import java.util.ArrayList;
import java.util.Map;

import dinosaur.sample.com.dinosaur.utils.Utils;

public class GameState {

    private static int mOptionCount = 4;
    private static int mCurrentIndex;
    private static int mTotalCount;
    private static int mCorrectCounter;
    private static Map<Integer,Dinosaur> mGameObjects;

    private static GameState mInstance = null;

    private GameState(){

    }
    public static GameState getInstance(){
        if(mInstance == null){
            mInstance = new GameState();
            initObjects();
        }
        return mInstance;
    }
    private static void initObjects(){
        mGameObjects = Utils.generateGameObjects(0);
        mCurrentIndex = 1;
        mTotalCount = mGameObjects.size();
        mCorrectCounter = 0;
    }

    //Method called by GameActivity to load the questions
    public  ArrayList<Dinosaur> getNextOptions(){
        ArrayList<Dinosaur> returnList = new ArrayList<Dinosaur>(mOptionCount);
        ArrayList<Integer> randomNumbers = generateRandomOptions();
        for(int i:randomNumbers){
             returnList.add(mGameObjects.get(i));
        }
        return returnList;
    }
    //Utility method for generations 4 random numbers
    private  ArrayList<Integer> generateRandomOptions(){
        return Utils.generateRandomNumbers(mCurrentIndex,mTotalCount,mOptionCount);
    }

    public void processCorrectAnswer(){
        mCorrectCounter++;
    }

    public void continueQuiz(){
        mCurrentIndex++;
    }

    public Dinosaur getCurrentGameObject(){
        return mGameObjects.get(mCurrentIndex);
    }

    public Dinosaur getGameObject(int index){
        return mGameObjects.get(index);
    }

    public int getmTotalCount(){
        return mTotalCount;
    }

    public int getmCurrentIndex(){
        return mCurrentIndex;
    }

    public int getmCorrectCounter(){
        return mCorrectCounter;
    }
}
