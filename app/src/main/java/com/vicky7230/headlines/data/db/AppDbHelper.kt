package com.vicky7230.headlines.data.db

import com.vicky7230.headlines.data.db.room.AppDatabase
import com.vicky7230.headlines.data.network.model.Article
import io.reactivex.Flowable
import javax.inject.Inject

class AppDbHelper @Inject
constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun insertArticle(articles: MutableList<Article>): List<Long> {
        return appDatabase.articleDao().insertArticle(articles)
    }

    override fun selectArticles(): Flowable<MutableList<Article>> {
        return appDatabase.articleDao().selectArticles()
    }

}