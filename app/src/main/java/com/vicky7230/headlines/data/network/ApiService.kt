package com.vicky7230.headlines.data.network

import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getArticles(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
    ): Observable<Headlines?>
}