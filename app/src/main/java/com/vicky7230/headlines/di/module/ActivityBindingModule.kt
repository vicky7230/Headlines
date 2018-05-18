package com.vicky7230.headlines.di.module

import com.vicky7230.headlines.ui.news.NewsActivity
import com.vicky7230.headlines.ui.news.NewsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(NewsActivityModule::class)])
    abstract fun bindNewsActivity(): NewsActivity
}