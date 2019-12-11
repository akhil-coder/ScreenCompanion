package com.appface.akhil.screencompanion;


public interface SearchMoviesScreenContract {

    interface View extends BaseView<Presenter> {
        void setupSearch();
    }

    interface  Presenter extends BasePresenter{

    }
}
