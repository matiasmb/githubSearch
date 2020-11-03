package com.matiasmb.githubsearch.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.matiasmb.githubsearch.R
import com.matiasmb.githubsearch.presentation.adapter.holder.ItemHeaderViewHolder
import com.matiasmb.githubsearch.presentation.adapter.holder.ItemViewHolder
import com.matiasmb.githubsearch.presentation.model.ItemView

class ItemsAdapter(
    private val items: ArrayList<ItemView>,
    private val context: Context,
    private val itemClickedListener: ItemClickedListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderHandler {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position]) {
            is ItemView.ViewRepo -> {
                (holder as ItemViewHolder).onBindViewHolder(
                    (items[position] as ItemView.ViewRepo).name,
                    (items[position] as ItemView.ViewRepo).url,
                    itemClickedListener
                )
            }
            is ItemView.ViewUserHeader -> {
                (holder as ItemHeaderViewHolder).onBindViewHolder(
                    (items[position] as ItemView.ViewUserHeader).name,
                    itemClickedListener
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
