package com.example.githubsearch.presentation.adapter.holder

import android.view.View
import kotlinx.android.synthetic.main.sticky_header.view.*

class ItemHeaderViewHolder(itemView: View) :  BaseItemViewHolder(itemView) {

    override fun onBindViewHolder(name: String, url: String) {
        itemView.username.text = name
    }

}
