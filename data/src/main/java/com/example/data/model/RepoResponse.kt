package com.example.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Collections.emptyList

@Parcelize
data class RepoResponse(
    @SerializedName("total_count") val total: Int?? = 0,
    @SerializedName("items") val items: List<RepoDetails> = emptyList()
):Parcelable

@Parcelize
data class RepoDetails(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("html_url") val url: String?,
    @SerializedName("stargazers_count") val stars: Int?,
    @SerializedName("forks_count") val forks: Int?,
    @SerializedName("language") val language: String?,
    @SerializedName("watchers") val watchers: Int?,
    @SerializedName("created_at") val createDate: String?,
    @SerializedName("updated_at") val updateDate: String?,
    @SerializedName("open_issues") val openIssues: Int?,
    @SerializedName("owner") val owner: OwnerDetails?,
    @SerializedName("watchers_count") val watchers_count: Int?,
    var isSelected:Boolean = false
) : Parcelable

@Parcelize
data class OwnerDetails(
    @SerializedName("avatar_url") val avatar_url:String?,
    @SerializedName("login") val login:String?
):Parcelable
