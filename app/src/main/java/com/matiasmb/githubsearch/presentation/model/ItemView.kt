package com.matiasmb.githubsearch.presentation.model

sealed class ItemView {
    class ViewRepo(val name: String, val url: String) : ItemView()
    class ViewUserHeader(val name: String) : ItemView()
}