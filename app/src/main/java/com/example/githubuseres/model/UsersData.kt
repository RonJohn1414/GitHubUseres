package com.example.githubuseres.model

import com.google.gson.annotations.SerializedName

class UsersData (

    @SerializedName("login")
    val loginName: String?,
    @SerializedName("repos_url")
    val repos: String?,
    @SerializedName("avatar_url")
    val avatar: String?

)