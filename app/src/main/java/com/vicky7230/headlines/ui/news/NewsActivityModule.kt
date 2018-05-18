package com.vicky7230.headlines.ui.news

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.headlines.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {

    /*@Provides
    fun provideRecipesAdapter(): NewsAdapter {
        return NewsAdapter(ArrayList())
    }*/

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}