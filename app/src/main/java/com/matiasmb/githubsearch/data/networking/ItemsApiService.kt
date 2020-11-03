package com.matiasmb.githubsearch.data.networking

import retrofit2.Callback

interface ItemsApiService<T> : Callback<T> {

    interface ItemsApiServiceCallback<T> {
        fun onSuccess(response: T)

        fun onError()
    }

    fun getPostFromReddit(query: String)
    fun setItemsApiServiceCallback(itemsApiServiceCallback: ItemsApiServiceCallback<T>)
}