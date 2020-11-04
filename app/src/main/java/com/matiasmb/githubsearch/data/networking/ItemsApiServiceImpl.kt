package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.model.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class ItemsApiServiceImpl(
    private val apiClient: ItemsApiClient
) : ItemsApiService {

    override suspend fun getReposByUsername(query: String): Flow<Resource<List<GithubRepo>>> {
        return callbackFlow {
            offer(
                Resource.Success(
                    apiClient.searchReposByUsername(query)
                )
            )
            awaitClose {
                close()
            }
        }
    }
}