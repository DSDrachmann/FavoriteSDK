import com.android.ide.common.gradle.RELEASE

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    kotlin("kapt")
}

android {
    namespace = "com.Dandd.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)// Add room-ktx dependency
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                groupId = "com.github.dsdrachmann"
                artifactId = "favorite-sdk"
                version = "0.0.1"

                from(components["release"])
            }
            create<MavenPublication>("debug") {
                groupId = "com.github.dsdrachmann"
                artifactId = "favorite-sdk"
                version = "0.0.1"

                from(components["debug"])
            }
        }
    }
}