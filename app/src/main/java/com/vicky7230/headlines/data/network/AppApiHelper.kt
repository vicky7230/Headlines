package com.vicky7230.headlines.data.network

import com.vicky7230.headlines.data.Config
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getArticles(): Observable<Headlines?> {
        return apiService.getArticles("in", Config.API_KEY)
    }
}