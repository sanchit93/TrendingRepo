package com.example.data

import com.example.data.repository.TrendingRepositoryImpl
import com.example.domain.repository.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun trendingRepo(trendingRepository:TrendingRepositoryImpl): TrendingRepository {
        return trendingRepository
    }
}