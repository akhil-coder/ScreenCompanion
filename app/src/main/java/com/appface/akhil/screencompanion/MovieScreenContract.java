package com.appface.akhil.screencompanion;

public interface MovieScreenContract {

    interface View{
        void setText(String str);
    }

    interface Presenter{
        void getText();
    }
}
