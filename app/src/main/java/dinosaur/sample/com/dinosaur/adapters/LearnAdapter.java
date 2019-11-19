package dinosaur.sample.com.dinosaur.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import dinosaur.sample.com.dinosaur.R;
import dinosaur.sample.com.dinosaur.data.Dinosaur;
import dinosaur.sample.com.dinosaur.utils.Utils;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ViewHolder> {

    private HashMap<Integer, Dinosaur> values;
    private Context mContext;

    public LearnAdapter(Context ctx) {
        this.mContext = ctx;
        values = Utils.generateGameObjects(0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Utils.LogOut(mContext , "oncreateViewHolder: "+i);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.row_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Utils.LogOut(mContext , "onBindViewHolder: "+i);
        Dinosaur dino = values.get(i+1);
        if(dino != null) {
            viewHolder.txtHeader.setText(dino.getName());
            viewHolder.imageView.setImageResource(dino.getDrawableID());
        }

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtHeader;
        public ImageView imageView;

        public ViewHolder(View v){
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imageView = (ImageView) v.findViewById(R.id.icon);
        }

    }

}
