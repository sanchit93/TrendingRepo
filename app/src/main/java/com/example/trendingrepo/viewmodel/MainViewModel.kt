package com.example.trendingrepo.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.TrendingRepository
import com.example.domain.model.RepoDataDetails
import com.example.domain.usecase.TrendingRepoUseCase
import com.example.trendingrepo.R
import com.example.trendingrepo.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    val trendingRepoUseCase: TrendingRepoUseCase
): ViewModel(){

    val originalList = ObservableArrayList<RepoDataDetails>()
    val repoList = ObservableArrayList<RepoDataDetails>()
    val isLoading = ObservableBoolean(false)
    var dataFeched = false

    fun getTrendingRepo(){
        viewModelScope.launch {
            isLoading.set(true)
            if (Utility.isNetworkAvailable(context)) {
                val response = trendingRepoUseCase.getTrendingRepo()
                if (response.isSuccess()) {
                    originalList.clear()
                    response.data?.let {
                        originalList.addAll(it)
                        repoList.addAll(it)
                    }
                    dataFeched = true
                    isLoading.set(false)
                } else {
                    isLoading.set(false)
                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }
            } else {
                isLoading.set(false)
                Toast.makeText(
                    context,
                    context.getString(R.string.internetIsNotAvailable),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
