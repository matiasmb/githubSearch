package com.matiasmb.githubsearch.domain

import com.matiasmb.githubsearch.presentation.model.ItemView

interface DomainInteractor {

    interface CallbackSearch {
        fun onSearchComplete(response: ArrayList<ItemView>)
        fun onSearchFailure()
    }

    suspend fun performSearch(query: String)
    fun setCallbackSearch(callbackSearch: CallbackSearch)
}