
import org.gradle.api.JavaVersion

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}


val ANDROID_HOME by extra("F:\\Android\\sdk")

android {
    namespace = "com.techmagnet.travelplanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.techmagnet.travelplanner"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room components
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")


// Kotlin coroutines for Room
    implementation ("androidx.room:room-ktx:2.6.1")

// ViewModel + LiveData
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
//    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

// LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

// Kotlin extension for viewModels() delegate
    implementation ("androidx.fragment:fragment-ktx:1.6.2")



    // Uncomment these if needed:
    // implementation("androidx.room:room-runtime:2.6.1")
    // kapt("androidx.room:room-compiler:2.6.1")
    // implementation("androidx.room:room-ktx:2.6.1")
    // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    // implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
}
