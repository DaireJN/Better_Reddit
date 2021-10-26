package com.daire.betterreddit.presentation.subreddits

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.databinding.SubredditItemBinding
import com.daire.betterreddit.domain.model.subreddit.SubredditChildData
import com.daire.betterreddit.presentation.util.loadImage

class SubredditsAdapter(private val subredditItemClickListener: SubredditItemClickListener) :
    ListAdapter<SubredditChildData, SubredditsAdapter.SubredditViewHolder>(
        SubredditChildDataDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubredditsAdapter.SubredditViewHolder {
        val binding = SubredditItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubredditViewHolder(
            binding
        )
    }

    inner class SubredditViewHolder(private val binding: SubredditItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rootContext: Context = binding.root.context
        fun bind(subredditChildData: SubredditChildData) {
            binding.subredditIcon.loadImage(subredditChildData.iconImage, rootContext)
            binding.subredditName.text = subredditChildData.displayName
            binding.root.setOnClickListener {
                subredditItemClickListener.onItemSelected(absoluteAdapterPosition, subredditChildData)
            }
            binding.root.setOnLongClickListener {
                subredditItemClickListener.onItemLongClick(absoluteAdapterPosition, subredditChildData)
            }
        }
    }

    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        val subredditChildData = getItem(position)
        holder.bind(subredditChildData)
    }

}

object SubredditChildDataDiffCallback : DiffUtil.ItemCallback<SubredditChildData>() {
    override fun areItemsTheSame(
        oldItem: SubredditChildData,
        newItem: SubredditChildData
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: SubredditChildData,
        newItem: SubredditChildData
    ): Boolean {
        return oldItem.displayName == newItem.displayName
    }
}

interface SubredditItemClickListener {
    fun onItemSelected(position: Int, item: SubredditChildData)
    fun onItemLongClick(position: Int, item: SubredditChildData): Boolean
}