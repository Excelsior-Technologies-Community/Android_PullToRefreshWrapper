package com.ext.pulltorefresh

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class PullToRefreshWrapper private constructor(
    private val swipeRefreshLayout: SwipeRefreshLayout
) {

    private var onRefresh: (() -> Unit)? = null

    init {
        swipeRefreshLayout.setOnRefreshListener {
            onRefresh?.invoke()
        }
    }

    fun setOnRefreshListener(listener: () -> Unit) = apply {
        this.onRefresh = listener
    }

    fun setRefreshing(isRefreshing: Boolean) = apply {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    companion object {
        fun with(swipeRefreshLayout: SwipeRefreshLayout): PullToRefreshWrapper {
            return PullToRefreshWrapper(swipeRefreshLayout)
        }
    }
}