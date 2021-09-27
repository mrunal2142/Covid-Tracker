package com.md.covidtracker.models;

public class newsmodel {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public newsmodel(String title, String description, String imageUrl, String dateAndTime, String author , String url , String content) {
        this.title = title;
        this.description = description;
        this.urlToImage = imageUrl;
        this.publishedAt = dateAndTime;
        this.author = author;
        this.url = url;
        this.content = content;
    }

    String title, description, urlToImage, publishedAt, author,url,content;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return urlToImage;
    }

    public void setImageUrl(String imageUrl) {
        this.urlToImage = imageUrl;
    }

    public String getDateAndTime() {
        return publishedAt;
    }

    public void setDateAndTime(String dateAndTime) {
        this.publishedAt = dateAndTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
