package com.kepler.androidsamplemynewsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.support.interfaces.SetOnRecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.LayoutViewHolder> {

    private final SetOnRecyclerViewItemClickListener setOnRecyclerViewItemClickListener;
    private final List<Article> dataSet = new ArrayList<>();
    private final SimpleDateFormat formatOd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final SimpleDateFormat formatNew = new SimpleDateFormat("dd MMM, hh:mm a");
    public NewsAdapter(SetOnRecyclerViewItemClickListener setOnRecyclerViewItemClickListener) {
        this.setOnRecyclerViewItemClickListener = setOnRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public LayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new LayoutViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LayoutViewHolder holder, int position) {
        holder.onBindViewHolder(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void add(List<Article> articles) {
        dataSet.clear();
        dataSet.addAll(articles);
        notifyDataSetChanged();
    }

    public class LayoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.publishDate)
        TextView publishDate;
        @BindView(R.id.image)
        ImageView imageview;

        LayoutViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            setOnRecyclerViewItemClickListener.onItemClick(dataSet.get(getAdapterPosition()).getUrl());
        }

        void onBindViewHolder(Article article) {
            if (article == null)
                return;
            title.setText(article.getTitle());
            publishDate.setText(getParseDate(article.getPublishedAt()));
            Picasso.get()
                    .load(article.getImageUrl())
                    .placeholder(android.R.drawable.stat_sys_download_done)
                    .centerCrop()
                    .resize(100, 100)
                    .into(imageview);
        }

        private String getParseDate(String publishedAt) {
            try {
                return formatNew.format(formatOd.parse(publishedAt));
            } catch (ParseException e) {
                return  publishedAt;
            }
        }
    }



}