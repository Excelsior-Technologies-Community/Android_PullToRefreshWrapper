## PullToRefreshWrapper
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)

A lightweight, flexible, and developer-friendly **Pull-To-Refresh wrapper library** for Android (Kotlin), built on top of `SwipeRefreshLayout`.

It simplifies refresh handling with a clean API, better control, and enhanced user experience.

---

### Features

*  Simple and clean API (1–2 lines setup)
*  Custom color scheme support
*  Auto refresh trigger
*  Disable scrolling while refreshing
*  Manual start/stop control
*  Lightweight & easy to integrate
*  Works with RecyclerView, ScrollView, NestedScrollView

---

## Installation

### Step 1: Add JitPack

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add Dependency

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:pulltorefresh:1.0.0'
	}
```

---

## Usage

### 1. Add SwipeRefreshLayout in XML

```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:id="@+id/swipeRefresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

---

### 2. Initialize in Activity/Fragment

```kotlin
val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

val wrapper = PullToRefreshWrapper
    .with(swipeRefresh)
    .attachScrollableView(recyclerView)
    .disableScrollWhileRefreshing(true)
    .setColorScheme(
        Color.BLUE,
        Color.GREEN,
        Color.RED
    )
    .setOnRefreshListener {

        // Simulate API call
        Handler(Looper.getMainLooper()).postDelayed({
            wrapper.stopRefreshing()
        }, 2000)
    }
```

---

### Auto Refresh

Trigger refresh programmatically:

```kotlin
wrapper.autoRefresh()
```

Perfect for:

* First API call on screen load
* Retry mechanisms

---

## Available APIs

### 🔹 Set Refresh Listener

```kotlin
setOnRefreshListener { }
```

---

### 🔹 Start / Stop Refresh

```kotlin
wrapper.setRefreshing(true)
wrapper.stopRefreshing()
```

---

### Auto Refresh

```kotlin
wrapper.autoRefresh()
```

---

### Set Colors

```kotlin
wrapper.setColorScheme(Color.RED, Color.BLUE)
```

---

### Disable Scroll While Refreshing

```kotlin
wrapper.disableScrollWhileRefreshing(true)
```

---

### 🔹 Attach Scrollable View

```kotlin
wrapper.attachScrollableView(recyclerView)
```

---

## 💡 Best Practices

* Use with **RecyclerView** for best UX
* Call `stopRefreshing()` after API success/failure
* Use `autoRefresh()` for initial data loading

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
---
