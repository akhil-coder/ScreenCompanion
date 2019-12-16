package com.appface.akhil.screencompanion.view;

import android.content.Intent;
import android.os.Bundle;

import com.appface.akhil.screencompanion.BaseActivity;
import com.appface.akhil.screencompanion.MainScreenContract;
import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.MyAppDatabase;
import com.appface.akhil.screencompanion.presenter.MainActivityPresenterImplementation;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements MainScreenContract.View {

    private static final String TAG = "MainActivity";
    private MyAppDatabase myAppDatabase;
    final int MOVIE_CONST = 100;
    final int TVSHOW_CONST = 101;

    MainActivityPresenterImplementation presenterImplementation;
    Unbinder unbinder;

    @BindView(R.id.cv_movies)
    CardView cv_movies;

    @BindView(R.id.cv_tvshows)
    CardView cv_tvshows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//      Move to background
//      myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "TaskDB").build();
//      myAppDatabase.getToDoDao().getAllTasks();

        unbinder = ButterKnife.bind(this);

        cv_movies.setOnClickListener(v -> {
            presenterImplementation.onMoviesClicked();
        });

        cv_tvshows.setOnClickListener(v -> {

        });

        presenterImplementation = new MainActivityPresenterImplementation(this);
    }

    @Override
    public void showProgressBar() {
    }


    @Override
    public void startSearchActivity() {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        startActivity(intent);
    }

    @Override
    public void startMoviesActivity() {
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }

    @Override
    public void startTVshowsActivity() {

    }

    @Override
    public void changeTabFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        presenterImplementation.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
