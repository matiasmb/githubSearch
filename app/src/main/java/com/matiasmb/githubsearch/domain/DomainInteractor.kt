package com.matiasmb.githubsearch.domain

import com.matiasmb.githubsearch.data.model.Resource
import com.matiasmb.githubsearch.presentation.model.ItemView
import kotlinx.coroutines.flow.Flow

interface DomainInteractor {

    suspend fun performSearch(query: String): Flow<Resource<ArrayList<ItemView>>>
}