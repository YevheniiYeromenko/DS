package com.example.bicher.ds;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<TrainingProgram> trainingProgramList = new ArrayList<>();
    private Context context;

    public void setTrainingProgramList(List<TrainingProgram> trainingProgramList) {
        this.trainingProgramList = trainingProgramList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        this.context = parent.getContext();
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(trainingProgramList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return trainingProgramList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private RatingBar rbRaiting;
        private ImageView imPhoto;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            rbRaiting = itemView.findViewById(R.id.rbRaiting);
            imPhoto = itemView.findViewById(R.id.imPhoto);
        }

        public void bind(TrainingProgram program, Context context){
            tvName.setText(program.getName());
            rbRaiting.setRating(program.getRaiting());
            rbRaiting.setFocusable(false);
            //imPhoto.setBackgroundColor(Color.rgb(255,0,0));

            Picasso.with(context)
                .load(program.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imPhoto);
        }

    }
}
