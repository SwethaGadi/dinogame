package dinosaur.sample.com.dinosaur.utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import dinosaur.sample.com.dinosaur.data.Dinosaur;
import dinosaur.sample.com.dinosaur.R;

public class Utils {

    public static void LogOut(Context ctx, String message)
    {
        Log.d(ctx.getString(R.string.LOG_TAG),message);
    }

    //Method which generates a map of dinosaur objects
    public static HashMap<Integer,Dinosaur> generateGameObjects(int level){

        HashMap<Integer,Dinosaur> mDinoMap = new HashMap<Integer, Dinosaur>();
        mDinoMap.put(1,new Dinosaur(1,"Tyrannosaurus Rex", R.drawable.dino_1));
        mDinoMap.put(2,new Dinosaur(2,"Apatosaurus", R.drawable.dino_2));
        mDinoMap.put(3,new Dinosaur(3,"Euoplocephalus", R.drawable.dino_3));
        mDinoMap.put(4,new Dinosaur(4,"Velociraptor", R.drawable.dino_4));
        mDinoMap.put(5,new Dinosaur(5,"Majungasaurus", R.drawable.dino_5));
        mDinoMap.put(6,new Dinosaur(6,"Sarcosuchus", R.drawable.dino_6));
        mDinoMap.put(7,new Dinosaur(7,"Herrerasaurus", R.drawable.dino_7));
        mDinoMap.put(8,new Dinosaur(8,"Spinosaurus", R.drawable.dino_8));
        mDinoMap.put(9,new Dinosaur(9,"Dilophosaurus", R.drawable.dino_9));
        mDinoMap.put(10,new Dinosaur(10,"Pteranodon", R.drawable.dino_10));
        mDinoMap.put(11, new Dinosaur(11,"Diplodocus", R.drawable.dino_11));
        mDinoMap.put(12,new Dinosaur(12,"Stegosaurus", R.drawable.dino_12));
        mDinoMap.put(13,new Dinosaur(13,"Compsognathus", R.drawable.dino_13));
        mDinoMap.put(14,new Dinosaur(14,"Giganotosaurus", R.drawable.dino_14));

        return mDinoMap;
    }

    //Method to generate 4 random Images
    public static ArrayList<Integer> generateRandomNumbers (int currentIndex,int totalObjects,int count){

        if(totalObjects >= count) {
            //taking a set so that duplicates will not be allowed
            HashSet<Integer> randomSet = new HashSet<>(count);
            randomSet.add(currentIndex);
            Random rand = new Random();
            while (randomSet.size() < count) {
                randomSet.add(rand.nextInt(totalObjects) + 1);
            }

            // creating a ArrayList so that we can use the default shuffle method from collections.
            ArrayList<Integer> list = new ArrayList<Integer>(randomSet);
            Collections.shuffle(list); //so that the correct image wont be in the first position always

            randomSet = null;

            return list;
        }else
        {
            throw new IllegalArgumentException("Not enough data");
        }
    }
}

