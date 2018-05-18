package com.vicky7230.headlines.data.network

import com.vicky7230.headlines.data.Config
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getHeadLines(): Observable<Headlines> {
        return apiService.getHeadLines("in", Config.API_KEY)
    }
}