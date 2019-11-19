package dinosaur.sample.com.dinosaur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import dinosaur.sample.com.dinosaur.adapters.LearnAdapter;
import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.utils.Utils;

public class LearnActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Utils.LogOut(this,"getting my recycler view");
        recyclerView = findViewById(R.id.recycler_view);
        Utils.LogOut(this,"getting my recycler view");
        //recyclerView.setHasFixedSize(true);

//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        Utils.LogOut(this,"setting the laypout manager");
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Utils.LogOut(this,"done setting the laypout manager");

        mAdapter = new LearnAdapter(this);
        Utils.LogOut(this,"done creaitn gthe adapter");
       recyclerView.setAdapter(mAdapter);
        Utils.LogOut(this,"done setting the adapter");
         //new newTask().execute();



    }

}