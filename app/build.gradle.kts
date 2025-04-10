plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") /*version "2.1.0-1.0.29"*/
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android") /*version "2.51.1" apply false*/

    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "com.develop.ecommerceapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.develop.ecommerceapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
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
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt DI
    implementation(libs.dagger.hilt.android)
//    ksp(libs.dagger.hilt.compiler)
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    // Hilt for Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // ViewModel and Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Observe livedata as State
    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")

    // For Vertical GridLayout
    implementation("androidx.compose.foundation:foundation:1.4.3")



    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    ksp(libs.androidx.lifecycle.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Room DB
    implementation(libs.androidx.room.room.runtime)
    ksp(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.room.rxjava3)
    // to use coroutine with Room DB
    implementation(libs.androidx.room.ktx)

    // Image Loading
    implementation(libs.glide)
    implementation(libs.coil.compose)

    // Firebase
    implementation(platform(libs.com.google.firebase.firebase.bom))
    implementation("com.google.firebase:firebase-firestore")



}