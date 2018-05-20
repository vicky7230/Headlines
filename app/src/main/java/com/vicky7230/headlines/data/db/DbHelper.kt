package com.vicky7230.headlines.data.db

import com.vicky7230.headlines.data.network.model.Article
import io.reactivex.Flowable

interface DbHelper {

    fun insertArticle(articles: MutableList<Article>): List<Long>

    fun selectArticles(): Flowable<MutableList<Article>>

}