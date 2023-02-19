object Deps {
    object Hilt {
        private const val hilt = "2.43.2"
        const val ANDROID = "com.google.dagger:hilt-android:${hilt}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${hilt}"
    }

    object Lifecycle {
        private const val lifecycle = "2.4.0-alpha03"
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle}"
        const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle}"
        const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle}"
    }

    object Navigation {
        private const val navigation = "2.5.0"
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${navigation}"
        const val UI = "androidx.navigation:navigation-ui-ktx:${navigation}"
    }

    object Coroutines {
        private const val coroutines = "1.6.0"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}"
    }

    object ImageLoad {
        const val GLIDE = "com.github.bumptech.glide:glide:4.13.2"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.13.2"
    }

    object MapKit {
        const val lite = "com.yandex.android:maps.mobile:4.2.2-lite"
        const val full = "com.yandex.android:maps.mobile:4.2.2-full"
    }

    object UI {
        private const val constraintLayout = "2.1.4"
        private const val recyclerView = "1.2.1"
        private const val progressbar = "3.1.0"
        private const val fragmentKtx = "1.5.0"
        private const val activityKtx = "1.5.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${constraintLayout}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${recyclerView}"
        const val PROGRESS_BAR = "com.mikhaellopez:circularprogressbar:${progressbar}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${fragmentKtx}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${activityKtx}"
    }

    object Android {
        private const val androidCoreKtx = "1.7.0"
        private const val androidAppcompat = "1.4.2"
        private const val androidMaterial = "1.6.1"
        const val CORE_KTX = "androidx.core:core-ktx:${androidCoreKtx}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${androidAppcompat}"
        const val MATERIAL = "com.google.android.material:material:${androidMaterial}"
    }

    object Test {
        private const val testJUnit = "4.+"
        private const val testAndroidxJUnit = "1.1.2"
        private const val testEspresso = "3.3.0"
        private const val mockito = "4.0.0"
        const val JUNIT = "junit:junit:${testJUnit}"
        const val ANDROID_JUNIT = "androidx.test.ext:${testJUnit}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${testEspresso}"
        const val MOCKITO_CORE = "org.mockito:mockito-core:${mockito}"
        const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${mockito}"
    }
}