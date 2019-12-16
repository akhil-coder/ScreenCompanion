package com.appface.akhil.screencompanion;

public interface MovieScreenContract {

    interface View{
        void intialisePageAdapter();
        void startSearchMoviesActivity();
    }

    interface Presenter{
        void start();
        void onSearchMoviesClicked();
    }
}
