// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlinx.kover) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.detekt) apply false
}

extra.apply {
    // Android SDK and tools
    set("min_sdk_version", 21)
    set("target_sdk_version", 35)
    set("compile_sdk_version", 35)
}

apply(from = "$rootDir/config/versioning.gradle")
apply(from = "$rootDir/config/maven.gradle")

allprojects {
    group = "net.nicbell.material-lists"
    version = rootProject.extra.get("material_lists_version").toString()
}

apply(from = "$rootDir/config/kover.gradle")

subprojects {
    apply(from = "$rootDir/config/detekt.gradle")
}
