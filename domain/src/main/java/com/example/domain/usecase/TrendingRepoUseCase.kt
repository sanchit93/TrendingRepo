package com.example.domain.usecase

import com.example.domain.utils.Resource
import com.example.domain.model.RepoDataDetails
import com.example.domain.repository.TrendingRepository
import java.util.*
import javax.inject.Inject

class TrendingRepoUseCase @Inject constructor(
    val repository: TrendingRepository,
) {

    suspend fun getTrendingRepo(): Resource<ArrayList<RepoDataDetails>> {
        return repository.getTrendingRepo()
    }
}