package com.vicky7230.headlines.di.module

import android.app.Application
import android.content.Context
import com.vicky7230.headlines.HeadlinesApplication
import com.vicky7230.headlines.data.AppDataManager
import com.vicky7230.headlines.data.Config
import com.vicky7230.headlines.data.DataManager
import com.vicky7230.headlines.data.network.ApiHelper
import com.vicky7230.headlines.data.network.AppApiHelper
import com.vicky7230.headlines.di.ApplicationContext
import com.vicky7230.headlines.di.BaseUrl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(headlinesApplication: HeadlinesApplication): Context {
        return headlinesApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(headlinesApplication: HeadlinesApplication): Application {
        return headlinesApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

}