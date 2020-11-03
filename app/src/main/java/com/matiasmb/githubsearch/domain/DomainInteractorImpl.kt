package com.matiasmb.githubsearch.domain

import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.networking.ItemsApiService
import com.matiasmb.githubsearch.presentation.model.ItemView

class DomainInteractorImpl(
    private val itemsApiService: ItemsApiService<List<GithubRepo>>
) : DomainInteractor {

    private lateinit var callbackSearch: DomainInteractor.CallbackSearch

    override suspend fun performSearch(query: String) {
        val response = itemsApiService.getReposByUsername(query)
        val responseListView: ArrayList<ItemView> = ArrayList()
        responseListView.add(ItemView.ViewUserHeader(query))
        response.forEach { githubRepo ->
            responseListView.add(ItemView.ViewRepo(githubRepo.name, githubRepo.url))
        }
        callbackSearch.onSearchComplete(responseListView)
    }

    override fun setCallbackSearch(callbackSearch: DomainInteractor.CallbackSearch) {
        this.callbackSearch = callbackSearch
    }
}
