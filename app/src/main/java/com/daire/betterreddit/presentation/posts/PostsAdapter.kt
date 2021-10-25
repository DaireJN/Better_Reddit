package com.daire.betterreddit.presentation.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.R
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
        private val rootContext: Context = binding.root.context
        fun bind(child: Child) {
            binding.postTitleTv.text = child.postData.title
            binding.subredditTitleTv.text = child.postData.subreddit
            binding.submittedByTv.text =
                rootContext.getString(R.string.submission_by, child.postData.author)
            binding.votesCountTv.text = rootContext.getString(
                R.string.vote_count,
                child.postData.score
            )
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