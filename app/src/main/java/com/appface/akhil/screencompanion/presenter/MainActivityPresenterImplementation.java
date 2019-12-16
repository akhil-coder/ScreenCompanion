package com.appface.akhil.screencompanion.presenter;


import com.appface.akhil.screencompanion.MainScreenContract;

public class MainActivityPresenterImplementation implements MainScreenContract.Presenter {

    private final MainScreenContract.View view;
    final int MOVIE_CONST = 100;
    final int TVSHOW_CONST = 101;

    public MainActivityPresenterImplementation(MainScreenContract.View view) {
        this.view = view;
    }



    @Override
    public void onMoviesClicked() {
            view.startMoviesActivity();
    }

    @Override
    public void onTVshowsClicked() {

    }

    @Override
    public void onStart() {

    }
}
