package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface ItemsApiService {

    suspend fun getReposByUsername(query: String): Flow<Resource<List<GithubRepo>>>
}