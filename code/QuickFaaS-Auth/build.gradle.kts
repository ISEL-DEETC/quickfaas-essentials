/*
 * Copyright (c) 7/12/2022, Pexers (https://github.com/Pexers)
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "2.0.3"
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")                 // Sessions for tokens
    implementation("io.ktor:ktor-server-auth:$ktorVersion")                     // OAuth
    implementation("io.ktor:ktor-client-cio:$ktorVersion")                      // CIO engine for HTTP client
    implementation("ch.qos.logback:logback-classic:1.2.11")                     // Required by some libraries
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}