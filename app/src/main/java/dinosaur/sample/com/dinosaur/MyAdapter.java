package dinosaur.sample.com.dinosaur;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import dinosaur.sample.com.dinosaur.utils.Utils;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private HashMap<Integer,Dinosaur> values;

    public MyAdapter() {
        values = Utils.generateGameObjects(0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.row_layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Dinosaur dino = values.get(i+1);
        if(dino != null) {
            viewHolder.txtHeader.setText(dino.getName());
            viewHolder.txtFooter.setText("" + dino.getId());
            viewHolder.imageView.setImageResource(dino.getDrawableID());
        }

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView imageView;

        public ViewHolder(View v){
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imageView = (ImageView) v.findViewById(R.id.icon);
        }

    }

}
