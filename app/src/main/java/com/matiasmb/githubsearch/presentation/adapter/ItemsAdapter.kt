package com.example.githubsearch.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.example.githubsearch.R
import com.example.githubsearch.presentation.adapter.holder.BaseItemViewHolder
import com.example.githubsearch.presentation.adapter.holder.ItemHeaderViewHolder
import com.example.githubsearch.presentation.adapter.holder.ItemViewHolder
import com.example.githubsearch.presentation.model.ItemView

class ItemsAdapter(private val items: ArrayList<ItemView>, private val context: Context) :
    RecyclerView.Adapter<BaseItemViewHolder>(), StickyHeaderHandler {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        return when (viewType) {
            VIEW_USER_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sticky_header, parent, false)
                ItemHeaderViewHolder(view)
            }
            VIEW_REPO -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
                ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("View not supported")
        }
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        when (items[position]) {
            is ItemView.ViewRepo -> {
                (holder as ItemViewHolder).onBindViewHolder(
                    (items[position] as ItemView.ViewRepo).name,
                    (items[position] as ItemView.ViewRepo).url
                )
            }
            is ItemView.ViewUserHeader -> {
                (holder as ItemHeaderViewHolder).onBindViewHolder(
                    (items[position] as ItemView.ViewUserHeader).name,
                    (items[position] as ItemView.ViewUserHeader).url
                )
            }
        }
        setAnimation(holder.itemView)
    }

    override fun getAdapterData(): ArrayList<ItemView> = items

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemView.ViewUserHeader -> VIEW_USER_HEADER
            is ItemView.ViewRepo -> VIEW_REPO
        }
    }

    private fun setAnimation(viewToAnimate: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.right_in)
        viewToAnimate.startAnimation(animation)
    }

    private companion object {
        const val VIEW_USER_HEADER = 0
        const val VIEW_REPO = 1
    }
}
