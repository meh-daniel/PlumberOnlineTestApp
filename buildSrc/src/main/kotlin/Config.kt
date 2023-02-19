import org.gradle.api.JavaVersion

object Config {

    const val compileSdk = 32
    const val minSDK = 21
    const val targetSDK = 31

    const val release = "release"
    const val debug = "debug"
    const val packageName = "plumber.online.test.ru"

    object Options {
        val compileOptions = JavaVersion.VERSION_11
        const val kotlinOptions = "11"
    }

}