package com.vicky7230.headlines.ui.news

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vicky7230.headlines.R
import com.vicky7230.headlines.ViewModelFactory
import com.vicky7230.headlines.data.DataManager
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        newsViewModel = ViewModelProviders.of(this,
                ViewModelFactory.getInstance(
                        application,
                        dataManager,
                        compositeDisposable)
        ).get(NewsViewModel::class.java)
        init()
    }

    private fun init() {

    }
}
