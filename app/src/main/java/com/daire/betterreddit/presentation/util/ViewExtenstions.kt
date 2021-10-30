package com.daire.betterreddit.presentation.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daire.betterreddit.R
import com.google.android.material.bottomnavigation.BottomNavigationView


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

fun View.fadeToGone() {
    val animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)
    apply {
        animate()
            .alpha(0f)
            .setDuration(animationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = View.GONE
                }
            })
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


/**
 * Potentially animate showing a [BottomNavigationView].
 *
 * Abruptly changing the visibility leads to a re-layout of main content, animating
 * `translationY` leaves a gap where the view was that content does not fill.
 *
 * Instead, take a snapshot of the view, and animate this in, only changing the visibility (and
 * thus layout) when the animation completes.
 */
fun BottomNavigationView.show() {
    if (visibility == VISIBLE) return
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            val parent = parent as ViewGroup
            // View needs to be laid out to create a snapshot & know position to animate. If view isn't
            // laid out yet, need to do this manually.
            if (!isLaidOut) {
                measure(
                    View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
                )
                layout(parent.left, parent.height - measuredHeight, parent.right, parent.height)
            }

            val drawable = BitmapDrawable(context.resources, drawToBitmap())
            drawable.setBounds(left, parent.height, right, parent.height + height)
            parent.overlay.add(drawable)
            ValueAnimator.ofInt(parent.height, top).apply {
                startDelay = 100L
                duration = 300L
                interpolator = AnimationUtils.loadInterpolator(
                    context,
                    android.R.interpolator.linear_out_slow_in
                )
                addUpdateListener {
                    val newTop = it.animatedValue as Int
                    drawable.setBounds(left, newTop, right, newTop + height)
                }
                doOnEnd {
                    parent.overlay.remove(drawable)
                    visibility = VISIBLE
                }
                start()
            }
        }
    })
}

/**
 * Potentially animate hiding a [BottomNavigationView].
 *
 * Abruptly changing the visibility leads to a re-layout of main content, animating
 * `translationY` leaves a gap where the view was that content does not fill.
 *
 * Instead, take a snapshot, instantly hide the view (so content lays out to fill), then animate
 * out the snapshot.
 */
fun BottomNavigationView.hide() {
    if (visibility == GONE) return
    viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            val drawable = BitmapDrawable(context.resources, drawToBitmap())
            val parent = parent as ViewGroup
            drawable.setBounds(left, top, right, bottom)
            parent.overlay.add(drawable)
            visibility = GONE
            ValueAnimator.ofInt(top, parent.height).apply {
                startDelay = 100L
                duration = 200L
                interpolator = AnimationUtils.loadInterpolator(
                    context,
                    android.R.interpolator.fast_out_linear_in
                )
                addUpdateListener {
                    val newTop = it.animatedValue as Int
                    drawable.setBounds(left, newTop, right, newTop + height)
                }
                doOnEnd {
                    parent.overlay.remove(drawable)
                }
                start()
            }
        }
    })
}
