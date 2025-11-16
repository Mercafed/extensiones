<<<<<<< HEAD
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://repo.cloudstream.cf")
}

dependencies {
    implementation("com.lagradost:cloudstream:3.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
=======
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://repo.cloudstream.cf")
}

dependencies {
    implementation("com.lagradost:cloudstream:3.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
>>>>>>> 04bce369166c5bc1859bf136f59a86bcfa8004f9
