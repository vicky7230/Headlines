package com.vicky7230.headlines.data.db.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vicky7230.headlines.data.network.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}