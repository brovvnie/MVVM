plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    compileSdk = 36
    namespace = "com.goldze.mvvmhabit"

    defaultConfig {
        applicationId = "com.goldze.mvvmhabit"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dataBinding {
        enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))

    // support
    implementation("com.google.android.material:material:1.0.0")

    // 下拉刷新,上拉加载
    implementation("com.lcodecorex:tkrefreshlayout:1.0.7")

    // 底部tabBar
    implementation("me.majiajie:pager-bottom-tab-strip:2.2.5") {
        exclude(group = "com.android.support")
    }

    // support
    api("androidx.legacy:legacy-support-v4:1.0.0")
    api("androidx.appcompat:appcompat:1.0.0")
    api("androidx.recyclerview:recyclerview:1.0.0")

    // rxjava
    api("io.reactivex.rxjava2:rxjava:2.2.3")
    api("io.reactivex.rxjava2:rxandroid:2.1.0")

    // rx管理View的生命周期
    api("com.trello.rxlifecycle2:rxlifecycle:2.2.2") {
        exclude(group = "com.android.support")
    }
    api("com.trello.rxlifecycle2:rxlifecycle-components:2.2.2") {
        exclude(group = "com.android.support")
    }

    // rxbinding
    api("com.jakewharton.rxbinding2:rxbinding:2.1.1") {
        exclude(group = "com.android.support")
    }

    // rx权限请求
    api("com.github.tbruyelle:rxpermissions:0.10.2") {
        exclude(group = "com.android.support")
    }

    // network
    api("com.squareup.okhttp3:okhttp:3.10.0")
    api("com.squareup.retrofit2:retrofit:2.4.0")
    api("com.squareup.retrofit2:converter-gson:2.4.0")
    api("com.squareup.retrofit2:adapter-rxjava2:2.4.0")

    // json解析
    api("com.google.code.gson:gson:2.8.6")

    // material-dialogs
    api("com.afollestad.material-dialogs:core:0.9.6.0") {
        exclude(group = "com.android.support")
    }
    api("com.afollestad.material-dialogs:commons:0.9.6.0") {
        exclude(group = "com.android.support")
    }

    // glide图片加载库
    api("com.github.bumptech.glide:glide:4.11.0") {
        exclude(group = "com.android.support")
    }
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    // recyclerview的databinding套装
    api("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:4.0.0") {
        exclude(group = "com.android.support")
    }
    api("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0") {
        exclude(group = "com.android.support")
    }
    api("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-viewpager2:4.0.0") {
        exclude(group = "com.android.support")
    }
    implementation(libs.bundles.lifecycle)
    implementation(libs.utilcode)
}
