package com.herdal.moviehouse

import Versions

object Libs {
    object AndroidX {
        const val core = "androidx.core:core-ktx:" + Versions.coreKtx
        const val appCompat = "androidx.appcompat:appcompat:" + Versions.appCompat
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:" + Versions.constraint
        const val vmLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.viewModel
        const val navigation = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:" + Versions.navigation
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.livedata
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:" + Versions.runtime
        const val extensions = "androidx.lifecycle:lifecycle-extensions:" + Versions.extensions
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.safeArgs
        const val swipe_refresh_layout =
            "androidx.swiperefreshlayout:swiperefreshlayout:" + Versions.swipe_refresh_layout
    }

    object Google {
        const val material = "com.google.android.material:material:" + Versions.material
        const val gson = "com.google.code.gson:gson:" + Versions.gson
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:" + Versions.hilt
        const val compiler = "com.google.dagger:hilt-android-compiler:" + Versions.hiltCompiler
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:" + Versions.hilt
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:" + Versions.retrofit
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit
        const val okhttp = "com.squareup.okhttp3:okhttp:" + Versions.ok_http
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.ok_http
        const val moshi = "com.squareup.moshi:moshi-kotlin:" + Versions.moshi
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:" + Versions.moshi
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:" + Versions.room
        const val runtime = "androidx.room:room-runtime:" + Versions.room
        const val compiler = "androidx.room:room-compiler:" + Versions.room
    }

    object Image {
        const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide
        const val compiler = "com.github.bumptech.glide:compiler:" + Versions.glide
        const val indicator = "me.relex:circleindicator:" + Versions.indicator
        const val viewPager = "androidx.viewpager2:viewpager2:" + Versions.viewPager
    }

    object Test {
        const val junit = "junit:junit:" + Versions.junit
        const val testExt = "androidx.test.ext:junit:" + Versions.testExt
        const val espresso = "androidx.test.espresso:espresso-core:" + Versions.espresso
    }

    object Pagination {
        const val paging = "androidx.paging:paging-runtime:" + Versions.paging
    }

    object Log {
        const val logger = "com.orhanobut:logger:" + Versions.logger
        const val timber = "com.jakewharton.timber:timber:" + Versions.timber
    }

    object SkyDoves {
        const val transformationLayout =
            "com.github.skydoves:transformationlayout:" + Versions.transformationLayout
        const val balloon = "com.github.skydoves:balloon:" + Versions.balloon
    }

    object View {
        const val lottie = "com.airbnb.android:lottie:" + Versions.lottie
    }
}