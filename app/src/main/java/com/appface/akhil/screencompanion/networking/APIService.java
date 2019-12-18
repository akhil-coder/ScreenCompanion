package com.appface.akhil.screencompanion.networking;



import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverResponse;
import com.appface.akhil.screencompanion.model.bean.movieSearch.MovieSearchResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("3/discover/movie")
    Call<MovieDiscoverResponse> discoverMovie(@Query("api_key") String api_key, @Query("page") int page, @Query("sort_by") String sort_by);

    @GET("3/search/movie")
    Single<MovieSearchResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);
}


