package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.data.model.GithubRepo

class ItemsApiServiceImpl(
    private val apiClient: ItemsApiClient
) : ItemsApiService<List<GithubRepo>> {

    override suspend fun getReposByUsername(query: String): List<GithubRepo> {
        return apiClient.searchReposByUsername(query)
    }
}