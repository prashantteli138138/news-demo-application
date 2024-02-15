package com.sigma.india.newsdemoapplicationpt0.data.repository;

import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;

import java.util.List;

// NewsRepository.java - Repository interface for fetching news data
public interface NewsRepository {
    List<NewsModel> getNews();
}

