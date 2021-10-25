package com.daire.betterreddit.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.databinding.SubredditPostItemBinding
import com.daire.betterreddit.domain.posts.Child

class PostsAdapter(
    private var children: List<Child>
) :
    ListAdapter<Child, PostsAdapter.PostsViewHolder>(ChildDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsAdapter.PostsViewHolder {
        val binding = SubredditPostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostsViewHolder(
            binding
        )
    }

    override fun getItemCount() = children.size

    override fun onBindViewHolder(holder: PostsAdapter.PostsViewHolder, position: Int) {
        val child = children[position]
        holder.bind(child)
    }

    inner class PostsViewHolder(private val binding: SubredditPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Child) {
            binding.postTitleTv.text = child.postData.title
            binding.subredditTitleTv.text = child.postData.title
            binding.submittedByTv.text = child.postData.title
            binding.votesCountTv.text = child.postData.title
        }
    }

}

object ChildDiffCallback : DiffUtil.ItemCallback<Child>() {
    override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem.postData.title == newItem.postData.title
    }
}