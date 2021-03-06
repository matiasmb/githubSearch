package com.matiasmb.githubsearch.presentation.model

sealed class ItemsStateScreen {
    class ShowItems(val repos: ArrayList<ItemView>) : ItemsStateScreen()
    object ShowErrorLoading : ItemsStateScreen()
}