package com.example.githubsearch.domain

import com.example.githubsearch.data.model.GithubRepo
import com.example.githubsearch.data.networking.ItemsApiService
import com.example.githubsearch.presentation.model.ItemView

class DomainInteractorImpl(
    private val itemsApiService: ItemsApiService<List<GithubRepo>>
) : DomainInteractor, ItemsApiService.ItemsApiServiceCallback<List<GithubRepo>> {

    private lateinit var callbackSearch: DomainInteractor.CallbackSearch
    private lateinit var name: String

    init {
        itemsApiService.setItemsApiServiceCallback(this)
    }

    override fun performSearch(query: String) {
        name = query
        itemsApiService.getPostFromReddit(query)
    }

    override fun setCallbackSearch(callbackSearch: DomainInteractor.CallbackSearch) {
        this.callbackSearch = callbackSearch
    }

    override fun onSuccess(response: List<GithubRepo>) {
        val responseListView: ArrayList<ItemView> = ArrayList()
        responseListView.add(ItemView.ViewUserHeader(name, ""))
        response.forEach { githubRepo ->
            responseListView.add(ItemView.ViewRepo(githubRepo.name, githubRepo.url))
        }
        callbackSearch.onSearchComplete(responseListView)
    }

    override fun onError() {
        callbackSearch.onSearchFailure()
    }

}
