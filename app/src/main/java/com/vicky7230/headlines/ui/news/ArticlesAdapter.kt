package com.vicky7230.headlines.ui.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vicky7230.flux.utils.GlideApp
import com.vicky7230.headlines.R
import com.vicky7230.headlines.data.network.model.Article
import kotlinx.android.synthetic.main.article_list_tem.view.*

class ArticlesAdapter(private val articles: MutableList<Article>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Callback {
        fun onArticleClick(url: String)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun addItems(articles: MutableList<Article>?) {
        this.articles?.clear()
        articles?.let { this.articles?.addAll(it) }
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Article? {
        return if (position != RecyclerView.NO_POSITION) {
            articles?.get(position)
        } else
            null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val articleViewHolder = ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_list_tem, parent, false))
        articleViewHolder.itemView.setOnClickListener({
            val article: Article? = getItem(articleViewHolder.adapterPosition)
            if (article != null) {
                callback?.onArticleClick(article.url ?: "")
            }
        })
        return articleViewHolder
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).onBind(articles?.get(position))
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(article: Article?) {
            GlideApp
                    .with(itemView.context)
                    .load(article?.urlToImage)
                    .transition(withCrossFade())
                    .fitCenter()
                    .into(itemView.image)

            itemView.title.text = article?.title
            itemView.description.text = article?.description
        }
    }
}