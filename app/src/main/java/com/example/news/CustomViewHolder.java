package com.example.news;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title,text_source;
    ImageView img_headline;
    CardView card_view;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.text_title);
        text_source = itemView.findViewById(R.id.text_source);
        img_headline = itemView.findViewById(R.id.img_headline);
        card_view = itemView.findViewById(R.id.card_view);
    }

    public static class Adapter extends RecyclerView.Adapter<CustomViewHolder>{

        private Context context;
        private List<NewsHeadlines> headlines;
        private SelectListener listener;

        public Adapter(Context context, List<NewsHeadlines> headlines,SelectListener listener) {
            this.context = context;
            this.headlines = headlines;
            this.listener = listener;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.news_headlines,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

            holder.text_title.setText(headlines.get(position).getTitle());
            holder.text_source.setText(headlines.get(position).getSource().getName());

            if(headlines.get(position).getUrlToImage()!=null){
                Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);

            }

            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.OnNewsClicked(headlines.get(position));

                }
            });

        }

        @Override
        public int getItemCount() {
            return headlines.size();
        }
    }

}
