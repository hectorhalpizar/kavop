import org.gradle.kotlin.dsl.androidTestImplementation
import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test.v181)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.androidx.documentfile)
            implementation(libs.androidx.uiautomator)
        }
    }
}

android {
    namespace = "me.hectorhalpizar.kavop.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.compileSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }

    dependencies {
        // Library
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)

        // JUnit
        androidTestImplementation(libs.junit)

        // AndroidX Test
        androidTestImplementation(libs.androidx.junit.v115)
        androidTestImplementation(libs.androidx.runner)

        androidTestImplementation(kotlin("test"))

        androidTestImplementation(libs.jetbrains.kotlinx.coroutines.test)
        androidTestImplementation(libs.androidx.rules)

        androidTestImplementation(libs.androidx.espresso.intents)
        androidTestImplementation(libs.androidx.espresso.core)
    }
}

