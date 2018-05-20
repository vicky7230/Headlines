package com.vicky7230.headlines.ui.news

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.vicky7230.headlines.data.DataManager
import com.vicky7230.headlines.data.network.model.Article
import com.vicky7230.headlines.data.network.model.Headlines
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(
        context: Application,
        private val dataManager: DataManager)
    : AndroidViewModel(context) {

    fun getArticlesFromDatabase(): Observable<Headlines?> {
        return Observable.defer { Observable.just(dataManager.selectArticles()) }
                .map { it: List<Article> ->
                    val headlines: Headlines? = Headlines(
                            "OK",
                            20,
                            it)
                    headlines
                }
                .subscribeOn(Schedulers.computation())
    }

    fun getArticlesFromNetwork(): Observable<Headlines?> {
        return dataManager.getArticles()
                .map { it: Headlines? ->
                    if (it?.articles != null) {
                        dataManager.deleteArticles()
                        dataManager.insertArticles(it.articles as MutableList<Article>)
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
    }
}
