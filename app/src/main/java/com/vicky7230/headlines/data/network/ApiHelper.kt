package com.vicky7230.headlines.data.network

import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable

interface ApiHelper {
    fun getArticles(): Observable<Headlines?>
}