package com.example.gsonrecyclerimage.Utils

import com.example.gsonrecyclerimage.Photo
import retrofit2.*
import retrofit2.http.GET

interface InternetAPI {

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}