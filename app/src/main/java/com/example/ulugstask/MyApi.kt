package com.example.ulugstask

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("/users/{username}")
    fun login(@Path ("username") username: String):Observable<User>
    @GET("/users/{username}/repos")
    fun userinfo(@Path("username")username: String):Observable<List<Repos>>

}