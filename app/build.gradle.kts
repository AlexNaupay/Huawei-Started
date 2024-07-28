
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id ("com.huawei.agconnect")
}

//apply ( plugin = "com.huawei.agconnect")

android {
    namespace = "com.alexnaupay.hselfiecamera"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alexnaupay.hselfiecamera"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.appcompat)  // SupportActionBar

    implementation ("com.huawei.agconnect:agconnect-core:1.9.1.303")
    //Account Kit
    implementation("com.huawei.hms:hwid:6.11.0.300")
    //Push Kit
    implementation("com.huawei.hms:push:6.11.0.300")

    // Import the base SDK. And face APIs
    /*implementation("com.huawei.hms:ml-computer-vision-face:3.11.0.301")
    // Import the contour and key point detection model package.
    implementation("com.huawei.hms:ml-computer-vision-face-shape-point-model:3.7.0.301")
    // Import the facial expression detection model package.
    implementation("com.huawei.hms:ml-computer-vision-face-emotion-model:3.7.0.301")
    // Import the facial feature detection model package.
    implementation("com.huawei.hms:ml-computer-vision-face-feature-model:3.7.0.301")*/

    implementation("com.huawei.hms:maps:6.11.2.301")  // Map Kit
}
