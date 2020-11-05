package com.matiasmb.githubsearch.presentation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


object RecyclerViewMatcher {

    const val VIEW_USER_HEADER = 0
    const val VIEW_REPO = 1

    fun withItemCount(count: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count: $count")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount == count
            }
        }
    }

    fun checkElementsByUsernameSearch(searchName: String): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView test firs element $searchName")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.let {  it.getItemViewType(0) == VIEW_USER_HEADER && it.getItemViewType(1) == VIEW_REPO } ?: false
            }
        }
    }
}