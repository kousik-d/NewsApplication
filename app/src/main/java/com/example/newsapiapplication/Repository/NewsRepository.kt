package com.example.newsapiapplication.Repository

import com.example.newsapiapplication.Network.retrofit
import com.example.newsapiapplication.Network.retrofitApi

class NewsRepository {

    suspend fun getTopNewsFromCountry(country : String,category: String)
    = retrofitApi.api.getTopNewsFromCountry(country,category)

    suspend fun getNewsByCompany(companyName : String,sortBy : String)
    = retrofitApi.api.getNewsByCompany(companyName,sortBy)

    suspend fun getNewsBetween(companyName: String,fromDate: String, toDate: String)
    = retrofitApi.api.getNewsBetween(companyName,fromDate,toDate)

    suspend fun getCompanyNewsFromLastMonth(companyName: String)
    = retrofitApi.api.getCompanyNewsFromLastMonth(companyName)
}