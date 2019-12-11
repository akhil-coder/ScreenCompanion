package com.appface.akhil.screencompanion.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appface.akhil.screencompanion.MovieScreenContract;
import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverViewModel;
import com.appface.akhil.screencompanion.presenter.MoviesFragmentPresenterImplementation;
import com.appface.akhil.screencompanion.view.adapter.MoviePagedListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesFragment extends Fragment implements MovieScreenContract.View {


    RecyclerView recyclerView;

    private Unbinder unbinder;
    MoviesFragmentPresenterImplementation presenterImplementation;
    View inflate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(getActivity());
        presenterImplementation = new MoviesFragmentPresenterImplementation(this);
        return inflate;
    }

    @Override
    public void intialisePageAdapter() {
        recyclerView = inflate.findViewById(R.id.rv_movielist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        MovieDiscoverViewModel movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);
        MoviePagedListAdapter myPagedListAdapter = new MoviePagedListAdapter(getActivity());
        movieDiscoverViewModel.pagedListLiveData.observe(this, movieDiscoverResponseResults -> myPagedListAdapter.submitList(movieDiscoverResponseResults));
        recyclerView.setAdapter(myPagedListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterImplementation.start();
    }
}
