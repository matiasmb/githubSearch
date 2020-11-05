package com.matiasmb.githubsearch.presentation.view

import android.content.Intent
import android.os.Build
import com.matiasmb.githubsearch.TestData.arrayDataList
import com.matiasmb.githubsearch.presentation.model.ItemsStateScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ItemsActivityTest : KoinTest {

    private lateinit var shadowActivity: ItemsActivity

    @Before
    fun setUp() {
        shadowActivity =
            Robolectric.buildActivity(ItemsActivity::class.java).create().resume().get()
    }

    @Test
    fun `supportActionBar SHOULD have a title set`() {
        assertEquals("Search Github Repos", shadowActivity.supportActionBar?.title)
    }

    @Test
    fun `loadScreen SHOULD set the recycler view list`() {
        shadowActivity.loadScreen(ItemsStateScreen.ShowItems(arrayDataList))
        assertNotNull(shadowActivity.list_item.adapter)
    }

    @Test
    fun `onItemClicked SHOULD call startActivity`() {
        val intent = shadowActivity.getItemIntent("https://github.com/test")
        assertNotNull(intent)
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals("https://github.com/test", intent.data.toString())
    }
}