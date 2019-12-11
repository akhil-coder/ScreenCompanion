package com.appface.akhil.screencompanion;



import androidx.fragment.app.Fragment;


public interface MainScreenContract {
    interface View extends BaseView<Presenter> {
        //        void showAllToDos(List<ToDo> toDoList);
//        void showError(String errorMessage);
//        boolean navigateToDataManipulationActivity(long id);
//        boolean navigateToSellerListActivity();

        void showProgressBar();
        void startSearchActivity();
        void changeTabFragment(Fragment fragment);

    }

    interface Presenter extends BasePresenter {
//        void onAddButtonClicked(String toDoItem, String place);
//        void onToDoItemSelected(long toDoId);
//        void onSellerListClicked();

        public void onSearchMoviesClicked();
        public void onBottomNavClicked(int s);
    }
}
