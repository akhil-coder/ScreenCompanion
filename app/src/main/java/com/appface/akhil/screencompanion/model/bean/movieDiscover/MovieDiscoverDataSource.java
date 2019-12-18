package com.appface.akhil.screencompanion.model.bean.movieDiscover;


import com.appface.akhil.screencompanion.networking.NetworkModule;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDiscoverDataSource extends PageKeyedDataSource<Integer, MovieDiscoverResponseResults> {

    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "TheMovieDB";
    private String sort_by = "";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieDiscoverResponseResults> callback) {
        NetworkModule.getInstance()
                .getApi()
                .discoverMovie("2dd3930734dabf88df8af1b709bd8748", FIRST_PAGE, sort_by)
                .enqueue(new Callback<MovieDiscoverResponse>() {
                    @Override
                    public void onResponse(Call<MovieDiscoverResponse> call, Response<MovieDiscoverResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDiscoverResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieDiscoverResponseResults> callback) {
        NetworkModule.getInstance()
                .getApi()
                .discoverMovie("2dd3930734dabf88df8af1b709bd8748", params.key, sort_by)
                .enqueue(new Callback<MovieDiscoverResponse>() {
                    @Override
                    public void onResponse(Call<MovieDiscoverResponse> call, Response<MovieDiscoverResponse> response) {

                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDiscoverResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieDiscoverResponseResults> callback) {
        NetworkModule.getInstance()
                .getApi()
                .discoverMovie("2dd3930734dabf88df8af1b709bd8748", params.key, sort_by)
                .enqueue(new Callback<MovieDiscoverResponse>() {
                    @Override
                    public void onResponse(Call<MovieDiscoverResponse> call, Response<MovieDiscoverResponse> response) {

                        if (response.body() != null) {
                            Integer key = params.key < 500 ? params.key + 1 : null;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDiscoverResponse> call, Throwable t) {

                    }
                });
    }

}
