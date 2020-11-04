package com.matiasmb.githubsearch.domain

import com.matiasmb.githubsearch.TestData.serviceFailureResponse
import com.matiasmb.githubsearch.TestData.serviceSuccessResponse
import com.matiasmb.githubsearch.data.model.Resource
import com.matiasmb.githubsearch.data.networking.ItemsApiService
import com.matiasmb.githubsearch.presentation.model.ItemView
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class DomainInteractorImplTest {

    private lateinit var domainInteractor: DomainInteractorImpl

    @Test
    fun `performSearch SHOULD return a ResourceSuccess with an array list of ItemView`() {
        runBlocking {
            // GIVEN
            val itemsApiService = mock<ItemsApiService> {
                onBlocking { getReposByUsername(anyString()) } doReturn serviceSuccessResponse
            }
            domainInteractor = DomainInteractorImpl(itemsApiService)

            //WHEN
            val response = domainInteractor.performSearch("google")

            //THEN
            response.collect {
                assertTrue(it is Resource.Success)
                assertTrue((it as Resource.Success).data.first() is ItemView.ViewUserHeader)
            }
        }
    }

    @Test
    fun `performSearch SHOULD return a ResourceFailure after receive an Failure from ApiService`() {
        runBlocking {
            // GIVEN
            val itemsApiService = mock<ItemsApiService> {
                onBlocking { getReposByUsername(anyString()) } doReturn serviceFailureResponse
            }
            domainInteractor = DomainInteractorImpl(itemsApiService)

            //WHEN
            val response = domainInteractor.performSearch("google")

            //THEN
            response.collect {
                assertTrue(it is Resource.Failure)
            }
        }
    }
}