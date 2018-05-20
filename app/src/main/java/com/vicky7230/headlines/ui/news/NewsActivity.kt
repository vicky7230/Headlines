package com.vicky7230.headlines.ui.news

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.vicky7230.headlines.R
import com.vicky7230.headlines.ViewModelFactory
import com.vicky7230.headlines.data.DataManager
import com.vicky7230.headlines.data.network.model.Article
import com.vicky7230.headlines.data.network.model.Headlines
import dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news.*
import timber.log.Timber
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsViewModel = ViewModelProviders.of(this,
                ViewModelFactory.getInstance(
                        application,
                        dataManager)
        ).get(NewsViewModel::class.java)

        init()
    }

    private fun init() {

        setSupportActionBar(toolbar as Toolbar?)

        refresh_layout.setOnRefreshListener {
            getHeadlines()
        }

        article_list.layoutManager = linearLayoutManager
        article_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        article_list.adapter = articlesAdapter

        getHeadlines()

    }

    private fun getHeadlines() {
        newsViewModel.getHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Headlines?> {
                    override fun onComplete() {
                        refresh_layout.isRefreshing = false
                    }

                    override fun onSubscribe(d: Disposable) {
                        refresh_layout.isRefreshing = true
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: Headlines) {
                        articlesAdapter.addItems(t.articles as MutableList<Article>?)
                    }

                    override fun onError(e: Throwable) {
                        refresh_layout.isRefreshing = false
                        Timber.e(e.message)
                    }
                })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
