package com.md.covidtracker.models;

import java.util.ArrayList;

public class newsmodel2 {

    String status;
    int totalResults;
    ArrayList<newsmodel> articles;

    public newsmodel2(String status, int totalResults, ArrayList<newsmodel> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<newsmodel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<newsmodel> articles) {
        this.articles = articles;
    }





}
