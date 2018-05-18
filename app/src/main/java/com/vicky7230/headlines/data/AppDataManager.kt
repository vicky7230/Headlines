package com.vicky7230.headlines.data

import com.vicky7230.headlines.data.network.AppApiHelper
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable
import javax.inject.Inject

class AppDataManager @Inject constructor(private val appApiHelper: AppApiHelper) : DataManager {

    override fun getHeadLines(): Observable<Headlines> {
        return appApiHelper.getHeadLines()
    }

}