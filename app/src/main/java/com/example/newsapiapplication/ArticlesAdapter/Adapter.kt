package com.example.newsapiapplication.ArticlesAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.newsapiapplication.Network.Article
import com.example.newsapiapplication.databinding.ArticleLayoutBinding

class ArticleAdapter(var articleList: List<Article>,
    val context : Context
):
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(val Articlebinding : ArticleLayoutBinding): RecyclerView.ViewHolder(Articlebinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val Article = articleList[position]
        Glide.with(context).load(Article.urlToImage)
            .into(holder.Articlebinding.ArticleImage)
        holder.Articlebinding.ArticletitleTextView.text = Article.title
        holder.Articlebinding.ArticleBodyTextView.text = "${Article.description.subSequence(0,100)}...."
    }
}