package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import kotlinx.android.synthetic.main.sticky_header.view.*

class ItemHeaderViewHolder(itemView: View) :  BaseItemViewHolder(itemView) {

    override fun onBindViewHolder(name: String, url: String, itemClickedListener: ItemClickedListener) {
        itemView.username.text = name
        itemView.header_card_layout.setOnClickListener { itemClickedListener.onItemClicked(url) }
    }
}
