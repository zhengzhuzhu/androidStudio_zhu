package com.example.app12_fragemnt2222;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {

    private ArrayList<Integer> imageViews;
    private ArrayList<String> newsString;

    public newsAdapter(ArrayList<Integer> imageViews, ArrayList<String> newsString) {
        this.imageViews = imageViews;
        this.newsString = newsString;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemviewClick;
        ImageView pic;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.text_View);
            itemviewClick = itemView;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str = newsString.get(position);
        int image = imageViews.get(position);
        holder.pic.setImageResource(image);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return newsString.size();
    }


}
