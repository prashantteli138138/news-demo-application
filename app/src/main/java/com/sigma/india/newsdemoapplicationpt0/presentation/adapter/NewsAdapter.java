package com.sigma.india.newsdemoapplicationpt0.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;

import java.util.List;

import android.view.LayoutInflater;

import com.sigma.india.newsdemoapplicationpt0.databinding.ItemNewsBinding;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsModel> newsList;

    public NewsAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNewsBinding itemBinding = ItemNewsBinding.inflate(layoutInflater, parent, false);
        return new NewsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel newsItem = newsList.get(position);
        holder.bind(newsItem);

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(newsItem.getUrlToImage())
                .into(holder.binding.newsImageView);
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNewsList(List<NewsModel> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final ItemNewsBinding binding;

        public NewsViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NewsModel newsItem) {
            binding.setNews(newsItem);
            binding.executePendingBindings();
        }
    }
}



