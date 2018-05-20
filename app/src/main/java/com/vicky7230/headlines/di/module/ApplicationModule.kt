package com.vicky7230.headlines.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.vicky7230.headlines.HeadlinesApplication
import com.vicky7230.headlines.data.AppDataManager
import com.vicky7230.headlines.data.Config
import com.vicky7230.headlines.data.DataManager
import com.vicky7230.headlines.data.db.AppDbHelper
import com.vicky7230.headlines.data.db.DbHelper
import com.vicky7230.headlines.data.db.room.AppDatabase
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
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Config.DB_NAME).build()
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

}