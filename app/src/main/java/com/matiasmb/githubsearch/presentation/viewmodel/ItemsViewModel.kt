package com.matiasmb.githubsearch.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matiasmb.githubsearch.domain.DomainInteractor
import com.matiasmb.githubsearch.presentation.model.ItemView
import com.matiasmb.githubsearch.presentation.model.ItemsStateScreen

class ItemsViewModel(private val domainInteractor: DomainInteractor) : ViewModel(),
    DomainInteractor.CallbackSearch {

    val stateScreen: LiveData<ItemsStateScreen> get() = _stateScreen
    private val _stateScreen: MutableLiveData<ItemsStateScreen> = MutableLiveData()

    init {
        domainInteractor.setCallbackSearch(this)
    }

    fun fetchItems(query: String) {
        domainInteractor.performSearch(query)
    }

    override fun onSearchComplete(response: ArrayList<ItemView>) {
        _stateScreen.value = ItemsStateScreen.ShowItems(response)
    }

    override fun onSearchFailure() {
        _stateScreen.value = ItemsStateScreen.ShowErrorLoading
    }
}