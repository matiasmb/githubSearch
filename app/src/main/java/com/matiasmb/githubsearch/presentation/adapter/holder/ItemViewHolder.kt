package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import com.matiasmb.githubsearch.presentation.model.ItemView
import kotlinx.android.synthetic.main.item_repo.view.*

class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun onBindViewHolder(
        data: ItemView,
        itemClickedListener: ItemClickedListener
    ) {
        val holderData = data as ItemView.ViewRepo
        itemView.repo_name.text = holderData.name
        itemView.card_layout.setOnClickListener { itemClickedListener.onItemClicked(holderData.url) }
    }
}