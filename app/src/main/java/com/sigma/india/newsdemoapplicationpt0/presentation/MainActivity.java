package com.sigma.india.newsdemoapplicationpt0.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.sigma.india.newsdemoapplicationpt0.R;
import com.sigma.india.newsdemoapplicationpt0.data.repository.NewsRepository;
import com.sigma.india.newsdemoapplicationpt0.data.repository.NewsRepositoryImpl;
import com.sigma.india.newsdemoapplicationpt0.domain.interactor.NewsInteractor;
import com.sigma.india.newsdemoapplicationpt0.presentation.adapter.NewsAdapter;
import com.sigma.india.newsdemoapplicationpt0.presentation.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private NewsViewModel newsViewModel;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel, Interactor, and Repository
        NewsRepository newsRepository = new NewsRepositoryImpl(this);
        NewsInteractor newsInteractor = new NewsInteractor(newsRepository);
        newsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(NewsViewModel.class);
        newsViewModel.init(newsInteractor); // Initialize NewsViewModel with NewsInteractor

        newsAdapter = new NewsAdapter(new ArrayList<>());
        recyclerView.setAdapter(newsAdapter);

        // Observe LiveData from ViewModel
        newsViewModel.getNews().observe(this, newsList -> {
            // Update UI with news data
            newsAdapter.setNewsList(newsList);
            newsAdapter.notifyDataSetChanged();
        });

        // Load news data
        newsViewModel.loadNews();
    }
}



