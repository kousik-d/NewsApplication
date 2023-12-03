package com.example.newsapiapplication.Network

import androidx.appcompat.text.AllCapsTransformationMethod
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=meta&from=2023-11-03&apiKey=85e54a79710d47108e7447168230d2ce
//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=85e54a79710d47108e7447168230d2ce

private val BASE_URL = "https://newsapi.org/"

const val API_KEY = "85e54a79710d47108e7447168230d2ce"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface NewsService {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun getTopNewsFromCountry(
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<News>

    @GET("v2/everything?apiKey=$API_KEY")
    suspend fun getNewsByCompany(
        @Query("q") company : String,
        @Query("sortBy") sortBy : String
    ): Response<News>

    @GET("v2/everything?apiKey=$API_KEY")
    suspend fun getNewsBetween(
        @Query("q") companyName : String,
        @Query("from") fromDate : String,
        @Query("to")  toDate : String,
    ): Response<News>


    @GET("v2/everything?apiKey=$API_KEY")
    suspend fun getCompanyNewsFromLastMonth(
        @Query("q") companyName: String,
        @Query("from") fromDate: String = "2023-11-03"
    ): Response<News>
}

object retrofitApi{
    val api : NewsService by lazy{
        retrofit.create(NewsService::class.java)
    }
}