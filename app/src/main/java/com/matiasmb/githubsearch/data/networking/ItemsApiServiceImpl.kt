package com.example.githubsearch.data.networking

import com.example.githubsearch.data.model.GithubRepo
import retrofit2.Call

class ItemsApiServiceImpl(
    private val apiClient: ItemsApiClient
) : ItemsApiService<List<GithubRepo>> {

    private lateinit var itemsApiServiceCallback: ItemsApiService.ItemsApiServiceCallback<List<GithubRepo>>

    override fun getPostFromReddit(query: String) {
        apiClient.searchReposByUsername(query).enqueue(this)
    }

    override fun onResponse(
        call: Call<List<GithubRepo>>,
        response: retrofit2.Response<List<GithubRepo>>
    ) {
        if (call.isExecuted && response.isSuccessful) {
            response.body()?.let {
                itemsApiServiceCallback.onSuccess(it)
            } ?: run { itemsApiServiceCallback.onError() }
        } else {
            itemsApiServiceCallback.onError()
        }
    }

    override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
        itemsApiServiceCallback.onError()
    }

    override fun setItemsApiServiceCallback(itemsApiServiceCallback: ItemsApiService.ItemsApiServiceCallback<List<GithubRepo>>) {
        this.itemsApiServiceCallback = itemsApiServiceCallback
    }
}