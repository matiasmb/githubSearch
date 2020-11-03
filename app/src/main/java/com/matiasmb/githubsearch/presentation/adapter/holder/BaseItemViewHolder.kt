package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener

abstract class BaseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    abstract fun onBindViewHolder(
        name: String,
        url: String,
        itemClickedListener: ItemClickedListener
    )
}