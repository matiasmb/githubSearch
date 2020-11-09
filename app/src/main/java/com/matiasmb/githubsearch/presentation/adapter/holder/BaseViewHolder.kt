package com.matiasmb.githubsearch.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matiasmb.githubsearch.presentation.adapter.ItemClickedListener
import com.matiasmb.githubsearch.presentation.model.ItemView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindViewHolder(data: ItemView, itemClickedListener: ItemClickedListener)
}