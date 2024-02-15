package com.sigma.india.newsdemoapplicationpt0.data.repository;

import android.content.Context;

import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// NewsRepositoryImpl.java - Implementation of NewsRepository
public class NewsRepositoryImpl implements NewsRepository {
    private final Context context;

    public NewsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<NewsModel> getNews() {
        List<NewsModel> newsList = new ArrayList<>();
        String json = loadJSONFromAsset("news.json");
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject articleObject = jsonArray.getJSONObject(i);
                    String publishedAt = articleObject.getString("publishedAt");
                    String title = articleObject.getString("title");
                    String url = articleObject.getString("url");
                    String urlToImage = articleObject.getString("urlToImage");

                    NewsModel newsModel = new NewsModel();
                    newsModel.setPublishedAt(publishedAt);
                    newsModel.setTitle(title);
                    newsModel.setUrl(url);
                    newsModel.setUrlToImage(urlToImage);

                    newsList.add(newsModel);
                }
                // Sort the list of news articles by published date
                newsList.sort(new Comparator<NewsModel>() {
                    @Override
                    public int compare(NewsModel news1, NewsModel news2) {
                        return news2.getPublishedAt().compareTo(news1.getPublishedAt());
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}

