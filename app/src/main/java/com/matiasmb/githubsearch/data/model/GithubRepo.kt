package com.matiasmb.githubsearch.data.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(val name: String, @SerializedName("html_url") val url: String)