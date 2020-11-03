package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import kotlinx.android.synthetic.main.sticky_header.view.*

class ItemHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBindViewHolder(name: String, itemClickedListener: ItemClickedListener) {
        itemView.username.text = name
        itemView.header_card_layout.setOnClickListener { itemClickedListener.onItemClicked(BASE_URL + name) }
    }

    private companion object {
        const val BASE_URL = "https://github.com/"
    }
}
