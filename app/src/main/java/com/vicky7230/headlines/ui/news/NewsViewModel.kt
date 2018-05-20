package com.vicky7230.headlines.ui.news

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.vicky7230.headlines.data.DataManager
import com.vicky7230.headlines.data.network.model.Article
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable

class NewsViewModel(
        context: Application,
        private val dataManager: DataManager)
    : AndroidViewModel(context) {

    fun getHeadlines(): Observable<Headlines> {
        return dataManager.getArticles()
                .map { it: Headlines? ->
                    if (it?.articles != null) {
                        dataManager.insertArticle(it.articles as MutableList<Article>)
                    }
                    return@map it
                }
    }
}
