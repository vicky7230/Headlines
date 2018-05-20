package com.vicky7230.headlines.data.db

import com.vicky7230.headlines.data.network.model.Article
import io.reactivex.Flowable

interface DbHelper {

    fun insertArticles(articles: MutableList<Article>): List<Long>

    fun selectArticles(): List<Article>

    fun deleteArticles()
}