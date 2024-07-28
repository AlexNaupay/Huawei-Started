// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("com.huawei.agconnect:agcp:1.9.1.303")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    id("com.huawei.agconnect") apply false
}

apply ( plugin = "com.huawei.agconnect")