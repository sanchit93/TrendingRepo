package com.example.domain.repository

import com.example.domain.utils.Resource
import com.example.domain.model.RepoDataDetails
import java.util.*


interface TrendingRepository  {
    suspend fun getTrendingRepo(): Resource<ArrayList<RepoDataDetails>>
}