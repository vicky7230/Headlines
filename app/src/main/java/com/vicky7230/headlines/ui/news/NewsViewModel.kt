package com.vicky7230.headlines.ui.news

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.vicky7230.headlines.data.DataManager
import io.reactivex.disposables.CompositeDisposable

class NewsViewModel(
        context: Application,
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable)
    : AndroidViewModel(context) {


}
