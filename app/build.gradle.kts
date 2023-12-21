plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.esraemirli.filmvault"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.esraemirli.filmvault"
        minSdk = 24
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
        kotlinCompilerExtensionVersion = Version.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:home:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation_api"))

    implementation(Android.core)
    implementation(Android.lifecycleRuntime)

    implementation(JetpackCompose.composeActivity)
    implementation(platform(JetpackCompose.composeBom))
    implementation(JetpackCompose.composeUi)
    implementation(JetpackCompose.composeUiGraphics)
    implementation(JetpackCompose.composeToolingPreview)
    implementation(JetpackCompose.composeMaterial3)
    implementation(JetpackCompose.navigation)

    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
    androidTestImplementation(platform(JetpackCompose.composeBom))
    androidTestImplementation(ComposeAndroidTestImplementation.composeUiTest)
    debugImplementation(ComposeAndroidTestImplementation.toolingUi)
    debugImplementation(ComposeAndroidTestImplementation.manifestTest)

    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltCompiler)

}