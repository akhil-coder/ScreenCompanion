package com.appface.akhil.screencompanion.presenter;

import com.appface.akhil.screencompanion.MovieScreenContract;

public class MoviesActivityPresenterImplementation implements MovieScreenContract.Presenter {

    MovieScreenContract.View view;

    public MoviesActivityPresenterImplementation(MovieScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.intialisePageAdapter();
    }

    @Override
    public void onSearchMoviesClicked() {
        view.startSearchMoviesActivity();
    }

    @Override
    public void onSortClicked() {

    }
}
