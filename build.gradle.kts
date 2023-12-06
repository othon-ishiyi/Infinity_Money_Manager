// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    java
    kotlin("jvm") version "1.4.32"
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

dependencies {
    implementation(files(".\\mysql-connector-j-8.1.0.jar"))
}