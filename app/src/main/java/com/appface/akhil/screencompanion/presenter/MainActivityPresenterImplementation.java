package com.appface.akhil.screencompanion.presenter;


import com.appface.akhil.screencompanion.MainScreenContract;
import com.appface.akhil.screencompanion.view.fragments.MoviesFragment;
import com.appface.akhil.screencompanion.view.fragments.TVShowsFragment;

public class MainActivityPresenterImplementation implements MainScreenContract.Presenter {

    private final MainScreenContract.View view;
    final int MOVIE_CONST = 100;
    final int TVSHOW_CONST = 101;

    public MainActivityPresenterImplementation(MainScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void onSearchMoviesClicked() {
        view.startSearchActivity();
    }

    @Override
    public void onBottomNavClicked(int s) {
        if (s == MOVIE_CONST)
            view.changeTabFragment(new MoviesFragment());
        else if (s == TVSHOW_CONST)
            view.changeTabFragment(new TVShowsFragment());
    }

    @Override
    public void onStart() {
        view.intialisePageAdapter();
    }
}
