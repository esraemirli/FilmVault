object Version {
    const val core = "1.12.0"
    const val appCompat = "1.6.1"
    const val lifecycleRuntime = "2.6.2"
    const val androidMaterial = "1.9.0"
    const val kotlinCompilerExtensionVersion = "1.5.2"
    const val kotlin = "1.9.0"

    //Compose
    const val composeActivity = "1.8.2"
    const val navigation = "2.7.0"
    const val composeUi = "1.2.0"
    const val composeMaterial = "1.2.0"
    const val composeMaterial3 = "1.1.2"
    const val coil = "2.5.0"

    //Hilt
    const val hilt = "2.48"
    const val hiltComposeNavigation = "1.0.0-alpha03"

    //Network
    const val retrofit = "2.9.0"
    const val gsonConverter = "2.9.0"
    const val okHttp = "4.10.0"
    const val coroutines = "1.5.1"

    //Test
    const val junit = "4.13.2"
    const val androidJunit = "1.1.5"
    const val espresso = "3.5.1"
}

object Android {
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntime}"
}

object JetpackCompose {
    const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
    const val composeUi = "androidx.compose.ui:ui:${Version.composeUi}"
    const val composeMaterial = "androidx.compose.material:material:${Version.composeMaterial}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Version.composeMaterial3}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeUi}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeBom = "androidx.compose:compose-bom:2023.08.00"
    const val navigation = "androidx.navigation:navigation-compose:${Version.navigation}"
}

object TestImplementation {
    const val junit = "junit:junit:${Version.junit}"
}

object AndroidTestImplementation {
    const val junit = "androidx.test.ext:junit:${Version.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
}

object ComposeAndroidTestImplementation {
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4"
    const val toolingUi = "androidx.compose.ui:ui-tooling"
    const val manifestTest = "androidx.compose.ui:ui-test-manifest"
}

object DaggerHilt {
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hilt}"
    const val hiltComposeNavigation =
        "androidx.hilt:hilt-navigation-compose:${Version.hiltComposeNavigation}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.gsonConverter}"
}

object Coroutines {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
}

object Image {
    const val coil = "io.coil-kt:coil-compose:${Version.coil}"
}