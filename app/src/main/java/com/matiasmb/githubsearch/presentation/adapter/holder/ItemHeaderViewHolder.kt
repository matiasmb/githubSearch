package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import com.matiasmb.githubsearch.presentation.model.ItemView
import kotlinx.android.synthetic.main.sticky_header.view.*

class ItemHeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun onBindViewHolder(data: ItemView, itemClickedListener: ItemClickedListener) {
        val holderData = data as ItemView.ViewUserHeader
        itemView.username.text = holderData.name
        itemView.header_card_layout.setOnClickListener { itemClickedListener.onItemClicked(BASE_URL + holderData.name) }
    }

    private companion object {
        const val BASE_URL = "https://github.com/"
    }
}
