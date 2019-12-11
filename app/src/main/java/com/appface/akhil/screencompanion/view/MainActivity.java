package com.appface.akhil.screencompanion.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.appface.akhil.screencompanion.BaseActivity;
import com.appface.akhil.screencompanion.MainScreenContract;
import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.MyAppDatabase;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverResponseResults;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverViewModel;
import com.appface.akhil.screencompanion.presenter.MainActivityPresenterImplementation;
import com.appface.akhil.screencompanion.view.adapter.MoviePagedListAdapter;
import com.appface.akhil.screencompanion.view.fragments.MoviesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements MainScreenContract.View {

    private static final String TAG = "MainActivity";
    private MyAppDatabase myAppDatabase;
    public static final String baseUrl = "http://api.themoviedb.org/";
    final int MOVIE_CONST = 100;
    final int TVSHOW_CONST = 101;

    MainActivityPresenterImplementation presenterImplementation;
    Unbinder unbinder;


    @BindView(R.id.rv_movielist)
    RecyclerView recyclerView;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Move to background
//      myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "TaskDB").build();
//      myAppDatabase.getToDoDao().getAllTasks();

        unbinder = ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
        presenterImplementation = new MainActivityPresenterImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_movies:
                    presenterImplementation.onBottomNavClicked(MOVIE_CONST);
                    break;
                case R.id.nav_tvshows:
                    presenterImplementation.onBottomNavClicked(TVSHOW_CONST);
                    break;
            }
            return true;
        }
    };

    @Override
    public void showProgressBar() {
    }

    @Override
    public void intialisePageAdapter() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MovieDiscoverViewModel movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);
        MoviePagedListAdapter myPagedListAdapter = new MoviePagedListAdapter(this);
        movieDiscoverViewModel.pagedListLiveData.observe(this, new Observer<PagedList<MovieDiscoverResponseResults>>() {
            @Override
            public void onChanged(PagedList<MovieDiscoverResponseResults> movieDiscoverResponseResults) {
                myPagedListAdapter.submitList(movieDiscoverResponseResults);
            }
        });
        recyclerView.setAdapter(myPagedListAdapter);
    }

    @Override
    public void startSearchActivity() {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        startActivity(intent);
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
        presenterImplementation.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_view:
                Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
                presenterImplementation.onSearchMoviesClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
