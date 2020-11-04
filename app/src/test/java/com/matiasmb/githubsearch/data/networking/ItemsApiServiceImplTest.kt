package com.matiasmb.githubsearch.data.networking

import com.matiasmb.githubsearch.TestData
import com.matiasmb.githubsearch.data.model.GithubRepo
import com.matiasmb.githubsearch.data.model.Resource
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class ItemsApiServiceImplTest {

    private lateinit var itemsApiService: ItemsApiServiceImpl

    @Test
    fun `getReposByUsername WHEN ItemsApiClient return a list of GithubRepo SHOULD return a list of GithubRepo`() {
        runBlocking {
            // GIVEN
            val apiClient = mock<ItemsApiClient> {
                onBlocking { searchReposByUsername(anyString()) } doReturn TestData.dataRepoResponseList
            }
            itemsApiService = ItemsApiServiceImpl(apiClient)

            //WHEN
            val response: Flow<Resource<List<GithubRepo>>> =
                itemsApiService.getReposByUsername("google")

            //THEN
            response.collect {
                assertTrue(it is Resource.Success)
            }
        }
    }

    @Test
    fun `getReposByUsername WHEN ItemsApiClient return an empty list of GithubRepo SHOULD return ResourceFailure`() {
        runBlocking {
            // GIVEN
            val apiClient = mock<ItemsApiClient> {
                onBlocking { searchReposByUsername(anyString()) } doReturn TestData.dataRepoResponseEmpty
            }
            itemsApiService = ItemsApiServiceImpl(apiClient)

            //WHEN
            val response: Flow<Resource<List<GithubRepo>>> =
                itemsApiService.getReposByUsername("google")

            //THEN
            response.collect {
                assertTrue(it is Resource.Failure)
            }
        }
    }

    @Test
    fun `getReposByUsername WHEN ItemsApiClient return an Exception SHOULD SHOULD return ResourceFailure`() {
        runBlocking {
            // GIVEN
            val apiClient = mock<ItemsApiClient> {
                onBlocking { searchReposByUsername(anyString()) } doAnswer { throw UnknownHostException() }
            }
            itemsApiService = ItemsApiServiceImpl(apiClient)

            //WHEN
            val response: Flow<Resource<List<GithubRepo>>> =
                itemsApiService.getReposByUsername("google")

            //THEN
            response.collect {
                assertTrue(it is Resource.Failure)
            }
        }
    }
}