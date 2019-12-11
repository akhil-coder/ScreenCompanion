package com.appface.akhil.screencompanion.model.bean.movieDiscover;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class MovieDiscoverDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MovieDiscoverResponseResults>> pageKeyedDataSourceMutableLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        MovieDiscoverDataSource movieDiscoverDataSource = new MovieDiscoverDataSource();
        pageKeyedDataSourceMutableLiveData.postValue(movieDiscoverDataSource);
        return movieDiscoverDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MovieDiscoverResponseResults>> getPageKeyedDataSourceMutableLiveData() {
        return pageKeyedDataSourceMutableLiveData;
    }
}
