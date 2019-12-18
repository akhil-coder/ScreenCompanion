package com.appface.akhil.screencompanion;

public interface MovieScreenContract {

    interface View{
        void intialisePageAdapter();
        void startSearchMoviesActivity();
        void startSortedDiscover(String s);
    }

    interface Presenter{
        void start();
        void onSearchMoviesClicked();
        void onSortClicked();
    }
}
