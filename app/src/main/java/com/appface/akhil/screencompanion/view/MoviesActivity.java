package com.appface.akhil.screencompanion.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.appface.akhil.screencompanion.BaseActivity;
import com.appface.akhil.screencompanion.MovieScreenContract;
import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverResponseResults;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverViewModel;
import com.appface.akhil.screencompanion.presenter.MoviesActivityPresenterImplementation;
import com.appface.akhil.screencompanion.view.adapter.MoviePagedListAdapter;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesActivity extends BaseActivity implements MovieScreenContract.View {

    RecyclerView recyclerView;
    private Unbinder unbinder;
    MoviesActivityPresenterImplementation presenterImplementation;

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    MovieDiscoverViewModel movieDiscoverViewModel;
    MoviePagedListAdapter myPagedListAdapter;
    private String sortParameter = "popularity.desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_drop);
        presenterImplementation = new MoviesActivityPresenterImplementation(this);
        unbinder = ButterKnife.bind(this);
        toolbar.setTitle(R.string.movies);
        setSupportActionBar(toolbar);
    }

    @Override
    public void intialisePageAdapter() {
        recyclerView = findViewById(R.id.rv_movielist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);


        myPagedListAdapter = new MoviePagedListAdapter(this);
        movieDiscoverViewModel.pagedListLiveData.observe(this, (PagedList<MovieDiscoverResponseResults> movieDiscoverResponseResults) -> {
            myPagedListAdapter.submitList(movieDiscoverResponseResults);
        });

        recyclerView.setAdapter(myPagedListAdapter);
    }

    @Override
    public void startSearchMoviesActivity() {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        startActivity(intent);
    }

    @Override
    public void startSortedDiscover(String s) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterImplementation.start();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
