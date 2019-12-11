package com.appface.akhil.screencompanion.model.bean.movieSearch;

import java.util.Arrays;
import java.util.List;

public class MovieSearchResponse implements java.io.Serializable {
    private static final long serialVersionUID = 7398424970434552219L;
    private int page;
    private int total_pages;
    private MovieResultItem[] results;
    private int total_results;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieResultItem> getResults() {
        return Arrays.asList(this.results);
    }

    public void setResults(MovieResultItem[] results) {
        this.results = results;
    }

    public int getTotal_results() {
        return this.total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "page=" + page +
                ", total_pages=" + total_pages +
                ", results=" + Arrays.toString(results) +
                ", total_results=" + total_results +
                '}';
    }
}
