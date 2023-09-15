package com.example.homeworkfragments

import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    fun getSuperheroes():Single<MutableList<Superheroes>>
}
