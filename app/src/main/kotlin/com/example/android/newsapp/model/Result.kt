package com.example.android.newsapp.model

data class Result(val id: String, val type: String, val sectionId: String,
                  val webPublicationDate: String, val webTitle: String,
                  val apiUrl: String, val isHosted: Boolean)

