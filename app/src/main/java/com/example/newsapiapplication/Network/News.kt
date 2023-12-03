package com.example.newsapiapplication.Network

data class News (
    val totalResults : Int,
    val articles : List<Article>
)