package com.example.githubsearch.domain

import com.example.githubsearch.presentation.model.ItemView

interface DomainInteractor {

    interface CallbackSearch {
        fun onSearchComplete(response: ArrayList<ItemView>)
        fun onSearchFailure()
    }

    fun performSearch(query: String)
    fun setCallbackSearch(callbackSearch: CallbackSearch)
}