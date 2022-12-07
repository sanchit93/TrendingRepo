package com.example.data.repository

import com.example.data.TrendingApi
import com.example.domain.utils.Resource
import com.example.data.toApiError
import com.example.data.toApiFailure
import com.example.domain.model.OwnerDataDetails
import com.example.domain.model.RepoDataDetails
import com.example.domain.repository.TrendingRepository
import java.util.*
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    val trendingApi: TrendingApi
) : TrendingRepository {
    override suspend fun getTrendingRepo(): Resource<ArrayList<RepoDataDetails>> {
        return try {
            val response = trendingApi.getTrendingRepo("android")
            if (response.isSuccessful) {
                val list = ArrayList<RepoDataDetails>(
                    response.body()?.items?.map{
                        data->RepoDataDetails(
                    id=data.id?:0L,
                    name = data.name?:"",
                    fullName = data.fullName?:"",
                    description = data.description?:"",
                    url = data.url?:"",
                    stars = data.stars?:0,
                    forks = data.forks?:0,
                    language = data.language?:"",
                    watchers = data.watchers?:0,
                    openIssues = data.openIssues?:0,
                    owner = OwnerDataDetails(
                        avatar_url = data?.owner?.avatar_url?:"",
                        login = data?.owner?.login?:""
                    ),
                    watchers_count = data.watchers_count?:0,
                    isSelected = false
                )
                })
                Resource.success(list)
            } else {
                Resource.error(response.toApiError())
            }
        } catch (throwable: Throwable) {
            Resource.error(throwable.toApiFailure())
        }
    }
}