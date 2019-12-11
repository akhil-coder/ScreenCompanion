package com.appface.akhil.screencompanion.presenter;


import com.appface.akhil.screencompanion.SearchMoviesScreenContract;

public class SearchMoviesActivityPresenterImplementation implements SearchMoviesScreenContract.Presenter {

    private final SearchMoviesScreenContract.View view;

    public SearchMoviesActivityPresenterImplementation(SearchMoviesScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.setupSearch();
    }
}
