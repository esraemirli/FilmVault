plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.feature.home.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.kotlinCompilerExtensionVersion
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
    implementation(project(":core:navigation_api"))
    implementation(project(":core:common"))
    implementation(project(":feature:home:domain"))

    implementation(Android.core)
    implementation(Android.appCompat)
    implementation(Android.androidMaterial)
    implementation("androidx.compose.material3:material3:1.1.2")
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)


    implementation(DaggerHilt.hilt)
    implementation(DaggerHilt.hiltComposeNavigation)
    kapt(DaggerHilt.hiltCompiler)

    implementation(JetpackCompose.navigation)
    implementation(JetpackCompose.composeMaterial3)
    implementation(JetpackCompose.composeBom)
    implementation(JetpackCompose.composeMaterial)
    implementation(Image.coil)
}