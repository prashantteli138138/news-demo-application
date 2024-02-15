package com.sigma.india.newsdemoapplicationpt0.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;
import com.sigma.india.newsdemoapplicationpt0.domain.interactor.NewsInteractor;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private final MutableLiveData<List<NewsModel>> newsLiveData = new MutableLiveData<>();
    private NewsInteractor newsInteractor;

    public void init(NewsInteractor newsInteractor) {
        this.newsInteractor = newsInteractor;
    }

    public LiveData<List<NewsModel>> getNews() {
        return newsLiveData;
    }

    public void loadNews() {
        newsInteractor.loadNews(new NewsInteractor.NewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsModel> newsList) {
                newsLiveData.setValue(newsList);
            }
        });
    }
}

