package com.xlwe.lectures

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://geek-jokes.sameerkumar.website/api?format=json")
    fun getJoke() : Call<JokeServerModel>
}