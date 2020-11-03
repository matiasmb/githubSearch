package com.example.githubsearch.presentation.adapter.holder

import android.view.View
import kotlinx.android.synthetic.main.item_repo.view.*

class ItemViewHolder(itemView: View) : BaseItemViewHolder(itemView) {

    override fun onBindViewHolder(name: String, url: String) {
        itemView.repo_name.text = name
    }
}