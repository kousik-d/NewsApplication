package com.example.newsapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapiapplication.ArticlesAdapter.ArticleAdapter
import com.example.newsapiapplication.Network.Article
import com.example.newsapiapplication.Network.retrofitApi
import com.example.newsapiapplication.Repository.NewsRepository
import com.example.newsapiapplication.ViewModel.NewsViewModel
import com.example.newsapiapplication.ViewModel.NewsViewModelFactory
import com.example.newsapiapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel

    lateinit var binding : ActivityMainBinding
    lateinit var articleAdapter : ArticleAdapter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list = mutableListOf<Article>()

        val newsRepository = NewsRepository()
        val newsViewModelFactory = NewsViewModelFactory(newsRepository)

        newsViewModel = ViewModelProvider(this,newsViewModelFactory).get(NewsViewModel::class.java)

        recyclerView = binding.ArticleRecycler
        articleAdapter = ArticleAdapter(list,this)
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getNewsOfCompany("Benz")
    }

    fun getNewsOfCompany(companyName : String){
        newsViewModel.getCompanyNewsFromLastM(companyName)
        newsViewModel.ArticlesFromCompanyLastM.observe(this, Observer {Articles ->
            articleAdapter.articleList = Articles
            articleAdapter.notifyDataSetChanged()
        })

    }

}