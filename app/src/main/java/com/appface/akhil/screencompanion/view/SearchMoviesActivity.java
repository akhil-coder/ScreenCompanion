package com.appface.akhil.screencompanion.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.appface.akhil.screencompanion.BaseActivity;
import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.SearchMoviesScreenContract;
import com.appface.akhil.screencompanion.model.bean.movieSearch.MovieResultItem;
import com.appface.akhil.screencompanion.model.bean.movieSearch.MovieSearchResponse;
import com.appface.akhil.screencompanion.networking.NetworkModule;
import com.appface.akhil.screencompanion.presenter.SearchMoviesActivityPresenterImplementation;
import com.appface.akhil.screencompanion.view.adapter.MovieSearchRecyclerViewAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchMoviesActivity extends BaseActivity implements SearchMoviesScreenContract.View, MovieSearchRecyclerViewAdapter.MovieSearchAdapterListener {

    private static final String TAG = "SearchMoviesActivity";
    @BindView(R.id.input_search)
    EditText inputSearch;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private List<MovieResultItem> moviesearchlist = new ArrayList<>();
    private MovieSearchRecyclerViewAdapter mAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private PublishSubject<String> publishSubject = PublishSubject.create();
    SearchMoviesActivityPresenterImplementation presenterImplementation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_search);

        unbinder = ButterKnife.bind(this);
        presenterImplementation = new SearchMoviesActivityPresenterImplementation(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    // TODO : On search list item click
    @Override
    public void onContactSelected(MovieResultItem contact) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterImplementation.onStart();
    }

    private DisposableObserver<TextViewTextChangeEvent> searchContactsTextWatcher() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                if (!textViewTextChangeEvent.text().toString().isEmpty())
                    publishSubject.onNext(textViewTextChangeEvent.text().toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    private DisposableObserver<MovieSearchResponse> getSearchObserver() {
        return new DisposableObserver<MovieSearchResponse>() {
            @Override
            public void onNext(MovieSearchResponse results) {
                moviesearchlist.clear();
                moviesearchlist.addAll(results.getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void setupRecyclerView() {
        mAdapter = new MovieSearchRecyclerViewAdapter(this, moviesearchlist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        whiteNotificationBar(recyclerView);
    }

    public void setupSearchObserver() {
        DisposableObserver<MovieSearchResponse> observer = getSearchObserver();
        // Ignores the previous emission and considers only the current search query. So the list will always displays the latest search results.
        disposable.add(
                publishSubject
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMapSingle((Function<String, Single<MovieSearchResponse>>) s -> NetworkModule.getInstance().getApi().searchMovie(getString(R.string.api_key), s, "1")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                        .subscribeWith(observer));

        // skipInitialValue() - skip for the first time when EditText empty
        disposable.add(
                RxTextView.textChangeEvents(inputSearch)
                        .skipInitialValue()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(searchContactsTextWatcher()));

        disposable.add(observer);

        // passing empty string fetches all the contacts
        publishSubject.onNext("A");
    }

    @Override
    public void setupSearch() {
        setupRecyclerView();
        setupSearchObserver();
    }

    @Override
    public void setPresenter(SearchMoviesScreenContract.Presenter presenter) {

    }
}
