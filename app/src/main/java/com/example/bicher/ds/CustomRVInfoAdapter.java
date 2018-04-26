package com.example.bicher.ds;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomRVInfoAdapter extends RecyclerView.Adapter<CustomRVInfoAdapter.CustomInfoHolder> {

    private List<String> stringList = new ArrayList<>();

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_list_item, parent,false);
        return new CustomInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomInfoHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class CustomInfoHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public CustomInfoHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvInfoItem);
        }
    }
}
