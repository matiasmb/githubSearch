package com.matiasmb.githubsearch

import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.model.Resource
import com.matiasmb.githubsearch.presentation.model.ItemView
import kotlinx.coroutines.flow.flow

object TestData {
    val arrayDataList = arrayListOf(
        ItemView.ViewUserHeader("google"),
        ItemView.ViewRepo("kotlin", "https://github.com/test"),
    )
    val dataList = flow {
        emit(
            Resource.Success(
                arrayDataList
            )
        )
    }
    val dataRepoResponseEmpty = emptyList<GithubRepo>()
    val dataRepoResponseList = listOf(
        GithubRepo("kotlin", "https://github.com/test"),
    )

    val serviceSuccessResponse = flow { emit(Resource.Success(dataRepoResponseList)) }
    val serviceFailureResponse = flow { emit(Resource.Failure) }
}