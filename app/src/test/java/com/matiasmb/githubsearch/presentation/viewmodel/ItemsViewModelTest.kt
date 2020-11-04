package com.matiasmb.githubsearch.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiasmb.githubsearch.CoroutinesRule
import com.matiasmb.githubsearch.TestData.dataList
import com.matiasmb.githubsearch.TestData.serviceFailureResponse
import com.matiasmb.githubsearch.domain.DomainInteractor
import com.matiasmb.githubsearch.getOrAwaitValue
import com.matiasmb.githubsearch.presentation.model.ItemsStateScreen
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class ItemsViewModelTest {

    private lateinit var viewModel: ItemsViewModel
    @get:Rule
    var coroutinesRule = CoroutinesRule()
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetchItems SHOULD call stateScreen liveData with ItemsStateScreen ShowItems`() {
        runBlocking {
            launch(Dispatchers.Main) {

                //GIVEN
                val domainInteractor = mock<DomainInteractor> {
                    onBlocking { performSearch(anyString()) } doReturn dataList
                }
                viewModel = ItemsViewModel(domainInteractor)

                //WHEN
                viewModel.fetchItems("google")

                //THEN
                assertTrue(viewModel.stateScreen.getOrAwaitValue() is ItemsStateScreen.ShowItems)
            }
        }
    }

    @Test
    fun `fetchItems SHOULD call stateScreen liveData with ItemsStateScreen ShowErrorLoading`() {
        runBlocking {
            launch(Dispatchers.Main) {

                //GIVEN
                val domainInteractor = mock<DomainInteractor> {
                    onBlocking { performSearch(anyString()) } doReturn serviceFailureResponse
                }
                viewModel = ItemsViewModel(domainInteractor)

                //WHEN
                viewModel.fetchItems("google")

                //THEN
                assertTrue(viewModel.stateScreen.getOrAwaitValue() is ItemsStateScreen.ShowErrorLoading)
            }
        }
    }
}