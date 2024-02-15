package com.sigma.india.newsdemoapplicationpt0.domain.interactor;


import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;
import com.sigma.india.newsdemoapplicationpt0.data.repository.NewsRepository;

import java.util.List;

public class NewsInteractor {
    private final NewsRepository newsRepository;

    public NewsInteractor(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void loadNews(NewsCallback callback) {
        List<NewsModel> newsList = newsRepository.getNews();
        // Perform any additional business logic
        callback.onNewsLoaded(newsList);
    }

    public interface NewsCallback {
        void onNewsLoaded(List<NewsModel> newsList);
    }
}

