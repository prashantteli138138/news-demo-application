package com.sigma.india.newsdemoapplicationpt0;

import android.content.Context;
import android.content.res.AssetManager;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.sigma.india.newsdemoapplicationpt0.data.model.NewsModel;
import com.sigma.india.newsdemoapplicationpt0.data.repository.NewsRepositoryImpl;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NewsRepositoryImplTest {

    @Mock
    private NewsRepositoryImpl newsRepository;

    @Mock
    private Context context;

    @Mock
    private AssetManager assetManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(context.getAssets()).thenReturn(assetManager); // Mock the behavior of context.getAssets() to return assetManager
    }

    @Test
    public void testGetNews_positive() {
        // Mock the JSON data for testing
        String json = "{\"articles\": [" +
                "{\"publishedAt\": \"2024-01-10T22:41:25Z\", \"title\": \"Title 1\", \"url\": \"url1\", \"urlToImage\": \"image1\"}," +
                "{\"publishedAt\": \"2024-01-11T18:18:13Z\", \"title\": \"Title 2\", \"url\": \"url2\", \"urlToImage\": \"image2\"}" +
                "]}";
        doReturn(json).when(newsRepository).loadJSONFromAsset("news.json");

        // Test the positive case where JSON is parsed and news are sorted by published date
        List<NewsModel> newsList = newsRepository.getNews();

        // Assert the size of the news list
        assertEquals(2, newsList.size());

        // Assert the ordering of news articles by their published date
        assertEquals("Title 2", newsList.get(0).getTitle());
        assertEquals("Title 1", newsList.get(1).getTitle());
    }

    @Test
    public void testGetNews_negative_missingJson() {
        // Test the negative case where JSON file is missing
        when(newsRepository.loadJSONFromAsset("news.json")).thenReturn(null);

        // Test that an empty list is returned when JSON file is missing
        List<NewsModel> newsList = newsRepository.getNews();

        assertEquals(0, newsList.size());
    }
}

