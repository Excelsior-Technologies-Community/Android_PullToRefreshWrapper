package com.ext.android_pulltorefreshwrapper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ext.pulltorefresh.PullToRefreshWrapper
import android.os.Handler
import android.os.Looper


class MainActivity : AppCompatActivity() {
    private lateinit var wrapper: PullToRefreshWrapper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        wrapper = PullToRefreshWrapper
            .with(swipeRefresh)
            .setOnRefreshListener {

                // Simulate API call
                Handler(Looper.getMainLooper()).postDelayed({
                    wrapper.setRefreshing(false)
                }, 2000)
            }
    }
}