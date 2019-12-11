package com.appface.akhil.screencompanion.model.bean.movieDiscover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class MovieDiscoverViewModel extends ViewModel {

    public LiveData<PagedList<MovieDiscoverResponseResults>> pagedListLiveData;
    public LiveData<PageKeyedDataSource<Integer, MovieDiscoverResponseResults>> liveDataSource;

    public MovieDiscoverViewModel() {
        MovieDiscoverDataSourceFactory movieDiscoverDataSourceFactory = new MovieDiscoverDataSourceFactory();
        liveDataSource = movieDiscoverDataSourceFactory.getPageKeyedDataSourceMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(50)
                .build();
        pagedListLiveData = (new LivePagedListBuilder(movieDiscoverDataSourceFactory, config)).build();
    }
}
