package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import kotlinx.android.synthetic.main.item_repo.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBindViewHolder(
        name: String,
        url: String,
        itemClickedListener: ItemClickedListener
    ) {
        itemView.repo_name.text = name
        itemView.card_layout.setOnClickListener { itemClickedListener.onItemClicked(url) }
    }
}