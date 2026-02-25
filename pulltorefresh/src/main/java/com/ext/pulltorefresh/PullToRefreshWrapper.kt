package com.ext.pulltorefresh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class PullToRefreshWrapper private constructor(
    private val swipeRefreshLayout: SwipeRefreshLayout
) {

    private var onRefresh: (() -> Unit)? = null
    private var attachedView: View? = null
    private var disableScrollWhileRefreshing = false

    init {
        swipeRefreshLayout.setOnRefreshListener {
            handleRefreshingState(true)
            onRefresh?.invoke()
        }
    }

    fun setOnRefreshListener(listener: () -> Unit) = apply {
        this.onRefresh = listener
    }

    /**
     * Attach scrollable view (RecyclerView / NestedScrollView etc.)
     */
    fun attachScrollableView(view: View) = apply {
        this.attachedView = view
    }

    /**
     * Enable/Disable scroll while refreshing
     */
    fun disableScrollWhileRefreshing(disable: Boolean) = apply {
        this.disableScrollWhileRefreshing = disable
    }

    /**
     * Set refreshing state manually
     */
    fun setRefreshing(isRefreshing: Boolean) = apply {
        swipeRefreshLayout.isRefreshing = isRefreshing
        handleRefreshingState(isRefreshing)
    }

    /**
     * Stop refreshing (shortcut)
     */
    fun stopRefreshing() = apply {
        setRefreshing(false)
    }

    /**
     * Auto trigger refresh
     */
    fun autoRefresh() = apply {
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            handleRefreshingState(true)
            onRefresh?.invoke()
        }
    }

    /**
     * Set color scheme
     */
    fun setColorScheme(vararg colors: Int) = apply {
        swipeRefreshLayout.setColorSchemeColors(*colors)
    }

    /**
     * Internal: handle scroll enable/disable
     */
    private fun handleRefreshingState(isRefreshing: Boolean) {
        if (disableScrollWhileRefreshing) {
            attachedView?.isEnabled = !isRefreshing

            // Special handling for RecyclerView
            if (attachedView is RecyclerView) {
                (attachedView as RecyclerView).suppressLayout(isRefreshing)
            }
        }
    }

    companion object {
        fun with(swipeRefreshLayout: SwipeRefreshLayout): PullToRefreshWrapper {
            return PullToRefreshWrapper(swipeRefreshLayout)
        }
    }
}