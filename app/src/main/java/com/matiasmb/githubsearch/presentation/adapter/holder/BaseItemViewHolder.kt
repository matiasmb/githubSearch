package com.example.githubsearch.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    abstract fun onBindViewHolder(name: String, url: String)
}