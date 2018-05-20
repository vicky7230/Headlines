package com.vicky7230.headlines.data.network.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(
        @PrimaryKey(autoGenerate = true)
        var id: Long,

        @SerializedName("source")
        @Expose
        @ColumnInfo(name = "source")
        var source: Source? = null,

        @SerializedName("author")
        @Expose
        @ColumnInfo(name = "author")
        var author: String? = null,

        @SerializedName("title")
        @Expose
        @ColumnInfo(name = "title")
        var title: String? = null,

        @SerializedName("description")
        @Expose
        @ColumnInfo(name = "description")
        var description: String? = null,

        @SerializedName("url")
        @Expose
        @ColumnInfo(name = "url")
        var url: String? = null,

        @SerializedName("urlToImage")
        @Expose
        @ColumnInfo(name = "urlToImage")
        var urlToImage: String? = null,

        @SerializedName("publishedAt")
        @Expose
        @ColumnInfo(name = "publishedAt")
        var publishedAt: String? = null
)
