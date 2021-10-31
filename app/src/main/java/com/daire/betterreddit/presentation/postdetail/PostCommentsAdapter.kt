package com.daire.betterreddit.presentation.postdetail

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.databinding.CommentItemBinding
import com.daire.betterreddit.domain.model.postdetail.PostReply
import com.daire.betterreddit.presentation.extensions.linkify

class PostCommentsAdapter :
    ListAdapter<PostReply, PostCommentsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postReply: PostReply) {
            binding.commentAuthorTxt.text = postReply.author
            binding.commentBodyTxt.text = postReply.commentBody
            binding.commentTimeTv.text = postReply.createdUtc.toString()
            binding.commentScore.text = postReply.score.toString()
            binding.commentUpvote.setOnClickListener { }
            binding.commentDownVote.setOnClickListener { }
            binding.commentBodyTxt.linkify()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PostReply>() {
        override fun areItemsTheSame(oldItem: PostReply, newItem: PostReply) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostReply, newItem: PostReply) =
            oldItem == newItem
    }
}