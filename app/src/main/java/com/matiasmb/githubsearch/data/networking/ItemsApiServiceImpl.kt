package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.model.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class ItemsApiServiceImpl(
    private val apiClient: ItemsApiClient
) : ItemsApiService {

    override suspend fun getReposByUsername(query: String): Flow<Resource<List<GithubRepo>>> {
        return flow {
            apiClient.searchReposByUsername(query).takeIf { it.isNotEmpty() }?.run {
                emit(
                    Resource.Success(this)
                )
            } ?: run {
                emit(
                    Resource.Failure
                )
            }
        }.catch {
            Resource.Failure
        }
    }
}