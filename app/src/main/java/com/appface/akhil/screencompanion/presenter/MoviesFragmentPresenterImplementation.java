package com.appface.akhil.screencompanion.presenter;


import com.appface.akhil.screencompanion.MovieScreenContract;

public class MoviesFragmentPresenterImplementation implements MovieScreenContract.Presenter {

    MovieScreenContract.View view;

    public MoviesFragmentPresenterImplementation(MovieScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void getText() {
        view.setText("MOVIES");
    }
}
