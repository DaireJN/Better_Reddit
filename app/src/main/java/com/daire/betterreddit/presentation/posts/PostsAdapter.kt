package com.daire.betterreddit.presentation.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.R
import com.daire.betterreddit.common.PostType
import com.daire.betterreddit.databinding.SubredditPostImageItemBinding
import com.daire.betterreddit.databinding.SubredditPostItemBinding
import com.daire.betterreddit.domain.model.posts.Child
import com.daire.betterreddit.presentation.util.loadImage

private const val VIEW_TYPE_DEFAULT_POST = 0
private const val VIEW_TYPE_IMAGE_POST = 1

class PostsAdapter(private val postClickListener: PostClickListener) :
    ListAdapter<Child, RecyclerView.ViewHolder>(ChildDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_DEFAULT_POST) {
            val binding = SubredditPostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PostsViewHolder(
                binding
            )
        } else {
            val binding = SubredditPostImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ImagePostViewHolder(
                binding
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).postData.postHint) {
            PostType.IMAGE.hint -> VIEW_TYPE_IMAGE_POST
            else -> VIEW_TYPE_DEFAULT_POST
        }
    }

    inner class PostsViewHolder(private val binding: SubredditPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rootContext: Context = binding.root.context
        fun bind(child: Child) {
            binding.postTitleTv.text = child.postData.title
            binding.subredditTitleTv.text = child.postData.subredditNameWithPrefix
            binding.submittedByTv.text =
                rootContext.getString(R.string.submission_by, child.postData.author)
            binding.votesCountTv.text = child.postData.score.toString()
            binding.commentCountTv.text = child.postData.numComments.toString()
            binding.root.setOnClickListener {
                postClickListener.onItemSelected(absoluteAdapterPosition, child)
            }
        }
    }

    inner class ImagePostViewHolder(private val binding: SubredditPostImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rootContext: Context = binding.root.context
        fun bind(child: Child) {
            binding.postImage.loadImage(child.postData.url, rootContext)
            binding.postTitleTv.text = child.postData.title
            binding.subredditTitleTv.text = child.postData.subredditNameWithPrefix
            binding.submittedByTv.text =
                rootContext.getString(R.string.submission_by, child.postData.author)
            binding.votesCountTv.text = child.postData.score.toString()
            binding.commentCountTv.text = child.postData.numComments.toString()
            binding.root.setOnClickListener {
                postClickListener.onItemSelected(absoluteAdapterPosition, child)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostsViewHolder -> {
                val child = getItem(position)
                holder.bind(child)
            }
            is ImagePostViewHolder -> {
                val child = getItem(position)
                holder.bind(child)
            }
        }
    }
}

object ChildDiffCallback : DiffUtil.ItemCallback<Child>() {
    override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
        return oldItem.postData.id == newItem.postData.id
    }
}

interface PostClickListener {
    fun onItemSelected(position: Int, item: Child)
}