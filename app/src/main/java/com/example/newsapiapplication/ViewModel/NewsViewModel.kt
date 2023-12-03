package com.example.newsapiapplication.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiapplication.Network.Article
import com.example.newsapiapplication.Repository.NewsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val TopNewsArticlesFromCountry : MutableLiveData<List<Article>> = MutableLiveData()

    val NewsArticlesFromCompany : MutableLiveData<List<Article>> = MutableLiveData()

    val ArticlesBetweenDates : MutableLiveData<List<Article>> = MutableLiveData()

    val ArticlesFromCompanyLastM : MutableLiveData<List<Article>> = MutableLiveData()


    fun getTopNewsCountry(country : String,category: String) = viewModelScope.launch(IO) {
        val response = newsRepository.getTopNewsFromCountry(country,category)
        if(response.isSuccessful){
            response.body()?.let{
                TopNewsArticlesFromCountry.value = it.articles
            }
        }
    }

    fun getNewsCompany(companyName : String,sortBy: String) = viewModelScope.launch(IO) {
        val response = newsRepository.getNewsByCompany(companyName,sortBy)
        if(response.isSuccessful){
            response.body()?.let{
                NewsArticlesFromCompany.value = it.articles
            }
        }
    }

    fun getNewsBetweenDates(companyName : String,from: String,to:String) = viewModelScope.launch(IO) {
        val response = newsRepository.getNewsBetween(companyName,from,to)
        if(response.isSuccessful){
            response.body()?.let{
                ArticlesBetweenDates.value = it.articles
            }
        }
    }

    fun getCompanyNewsFromLastM(companyName: String) = viewModelScope.launch(IO) {
        val response = newsRepository.getCompanyNewsFromLastMonth(companyName)
        if(response.isSuccessful){
            response.body()?.let{
                Log.i("TOTALRES","${it.totalResults}")
                ArticlesFromCompanyLastM.postValue(it.articles)
            }
        }
    }




}