package com.daire.betterreddit.presentation.utli

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(url: String?, context: Context) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
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
