package com.daire.betterreddit.presentation.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daire.betterreddit.R

fun ImageView.loadImage(url: String?, context: Context) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_book)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun GridLayoutManager.setSpanToFillRemainder(initialSpanCount: Int) {
    this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (val pos = position.plus(1)) {
                itemCount -> {
                    if (pos.rem(initialSpanCount) == 1) {
                        spanCount
                    } else {
                        1
                    }
                }
                else -> 1
            }
        }
    }
}


fun View.fadeToVisible() {
    val animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)
    apply {
        visibility = View.VISIBLE
        alpha = 0f
        animate()
            .alpha(1f)
            .setDuration(animationDuration.toLong())
            .setListener(null)
    }
}

fun View.fadeToInvisible() {
    val animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)
    apply {
        animate()
            .alpha(0f)
            .setDuration(animationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = View.INVISIBLE
                }
            })
    }
}
