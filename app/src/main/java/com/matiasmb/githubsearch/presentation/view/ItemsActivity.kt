package com.matiasmb.githubsearch.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.brandongogetap.stickyheaders.StickyLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matiasmb.githubsearch.R
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import com.matiasmb.githubsearch.presentation.adapter.ItemsAdapter
import com.matiasmb.githubsearch.presentation.model.ItemView
import com.matiasmb.githubsearch.presentation.model.ItemsStateScreen
import com.matiasmb.githubsearch.presentation.viewmodel.ItemsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ItemsActivity : AppCompatActivity(), ItemClickedListener {

    private val viewModel: ItemsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val myActionMenuItem = menu?.findItem(R.id.action_search)
        val searchView = myActionMenuItem?.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        myActionMenuItem.expandActionView()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.fetchItems(query)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                //Not implemented
                return true
            }
        })
        return true
    }

    private fun setObservers() {
        viewModel.stateScreen.observe(this, ::loadScreen)
    }

    private fun loadScreen(itemsStateScreen: ItemsStateScreen) {
        when (itemsStateScreen) {
            is ItemsStateScreen.ShowItems -> loadRepoList(itemsStateScreen.repos)
            is ItemsStateScreen.ShowErrorLoading -> showErrorScreen()
        }
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.add_repos_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showErrorScreen() {
        Snackbar.make(
            coordinator_layout,
            getString(R.string.error_searching_for_repos),
            Snackbar.LENGTH_LONG
        ).apply {
            view.setBackgroundColor(ContextCompat.getColor(this@ItemsActivity, R.color.red))
        }.show()
    }

    private fun loadRepoList(repoList: ArrayList<ItemView>) {
        val itemsAdapter = ItemsAdapter(repoList, context = this, itemClickedListener = this)
        list_item.apply {
            adapter = itemsAdapter
            setHasFixedSize(true)
            val stickyLayoutManager = StickyLayoutManager(this@ItemsActivity, itemsAdapter)
            stickyLayoutManager.elevateHeaders(true)
            layoutManager = stickyLayoutManager
        }
    }

    override fun onItemClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
