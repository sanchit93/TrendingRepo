package com.example.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoDataDetails(
    val id: Long?,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val url: String?,
    val stars: Int?,
    val forks: Int?,
    val language: String?,
    val watchers: Int?,
    val openIssues: Int?,
    val owner: OwnerDataDetails?,
    val watchers_count: Int?,
    var isSelected: Boolean = false
):Parcelable

@Parcelize
data class OwnerDataDetails(
    val avatar_url:String?,
    val login:String?
):Parcelable
