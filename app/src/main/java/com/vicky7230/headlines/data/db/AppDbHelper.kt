package com.vicky7230.headlines.data.db

import com.vicky7230.headlines.data.db.room.AppDatabase
import com.vicky7230.headlines.data.network.model.Article
import io.reactivex.Flowable
import javax.inject.Inject

class AppDbHelper @Inject
constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun insertArticles(articles: MutableList<Article>): List<Long> {
        return appDatabase.articleDao().insertArticles(articles)
    }

    override fun selectArticles(): List<Article> {
        return appDatabase.articleDao().selectArticles()
    }

    override fun deleteArticles() {
        appDatabase.articleDao().deleteArticles()
    }
}