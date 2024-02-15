package com.sigma.india.newsdemoapplicationpt0.data.model;

import com.sigma.india.newsdemoapplicationpt0.utils.DateUtils;

// NewsModel.java - Model class for News
public class NewsModel {
    private String publishedAt;
    private String title;
    private String url;
    private String urlToImage;

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getPublishedAtFormattedString() {
        if (publishedAt != null) {
            return DateUtils.parseDate(publishedAt).toString();
        } else {
            return "";
        }
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}

