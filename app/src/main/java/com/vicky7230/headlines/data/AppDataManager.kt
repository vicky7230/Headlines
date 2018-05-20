package com.vicky7230.headlines.data

import com.vicky7230.headlines.data.db.AppDbHelper
import com.vicky7230.headlines.data.network.AppApiHelper
import com.vicky7230.headlines.data.network.model.Article
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class AppDataManager @Inject constructor(private val appApiHelper: AppApiHelper, private val appDbHelper: AppDbHelper) : DataManager {

    override fun getArticles(): Observable<Headlines> {
        return appApiHelper.getArticles()
    }

    override fun insertArticle(articles: MutableList<Article>): List<Long> {
        return appDbHelper.insertArticle(articles)
    }

    override fun selectArticles(): Flowable<MutableList<Article>> {
        return appDbHelper.selectArticles()
    }
}