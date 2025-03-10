package com.fakhrifajar.myapplication.data.network.api

import com.fakhrifajar.myapplication.data.network.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Response
}