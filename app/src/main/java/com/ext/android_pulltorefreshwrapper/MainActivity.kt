package com.ext.android_pulltorefreshwrapper

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ext.pulltorefresh.PullToRefreshWrapper
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView


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
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        wrapper = PullToRefreshWrapper
            .with(swipeRefresh)
            .attachScrollableView(recyclerView)
            .disableScrollWhileRefreshing(true)
            .setColorScheme(
                Color.BLUE,
                Color.GREEN,
                Color.RED
            )
            .setOnRefreshListener {

                // API call simulation
                Handler(Looper.getMainLooper()).postDelayed({
                    wrapper.stopRefreshing()
                }, 2000)
            }
    }
}