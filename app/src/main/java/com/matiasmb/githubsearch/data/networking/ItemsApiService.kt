package com.matiasmb.githubsearch.data.networking

interface ItemsApiService<T> {

    suspend fun getReposByUsername(query: String): T
}