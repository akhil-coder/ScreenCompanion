package com.appface.akhil.screencompanion;

public interface MovieScreenContract {

    interface View{
        void intialisePageAdapter();
    }

    interface Presenter{
        void start();
    }
}
