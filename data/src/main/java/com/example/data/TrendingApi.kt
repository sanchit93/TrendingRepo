package com.example.data

import com.example.data.model.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApi {
    @GET("search/repositories")
    suspend fun getTrendingRepo(
        @Query("q") type:String
    ):Response<RepoResponse>
}