package com.matiasmb.githubsearch.domain

import com.matiasmb.githubsearch.data.model.Resource
import com.matiasmb.githubsearch.data.networking.ItemsApiService
import com.matiasmb.githubsearch.presentation.model.ItemView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class DomainInteractorImpl(
    private val itemsApiService: ItemsApiService
) : DomainInteractor {

    override suspend fun performSearch(query: String): Flow<Resource<ArrayList<ItemView>>> {
        return flow {
            itemsApiService.getReposByUsername(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        val responseListView: ArrayList<ItemView> = ArrayList()
                        responseListView.add(ItemView.ViewUserHeader(query))
                        response.data.forEach { githubRepo ->
                            responseListView.add(
                                ItemView.ViewRepo(
                                    githubRepo.name,
                                    githubRepo.url
                                )
                            )
                        }
                        emit(Resource.Success(responseListView))
                    }
                    is Resource.Failure -> emit(Resource.Failure)
                }
            }
        }
    }
}
