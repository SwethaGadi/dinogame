package dinosaur.sample.com.dinosaur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import dinosaur.sample.com.dinosaur.adapters.LearnAdapter;
import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.utils.Utils;

/**
 * A screen which lists all the dinosaurs along with their names.
 */
public class LearnActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mAdapter = new LearnAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

}