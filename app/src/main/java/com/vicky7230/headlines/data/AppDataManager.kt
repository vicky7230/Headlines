package com.vicky7230.headlines.data

import com.vicky7230.headlines.data.db.AppDbHelper
import com.vicky7230.headlines.data.network.AppApiHelper
import com.vicky7230.headlines.data.network.model.Article
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class AppDataManager @Inject constructor(private val appApiHelper: AppApiHelper, private val appDbHelper: AppDbHelper) : DataManager {

    override fun getArticles(): Observable<Headlines?> {
        return appApiHelper.getArticles()
    }

    override fun insertArticles(articles: MutableList<Article>): List<Long> {
        return appDbHelper.insertArticles(articles)
    }

    override fun selectArticles(): List<Article> {
        return appDbHelper.selectArticles()
    }

    override fun deleteArticles() {
        appDbHelper.deleteArticles()
    }

}