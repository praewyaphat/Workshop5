val kotlin_version: String by project
val logback_version: String by project
val ktor_version = "2.3.7"

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.2.1"
    kotlin("plugin.serialization") version "2.1.10" // 👈 เพิ่ม plugin serialization
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    // JSON serialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.2.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.2.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // (Optional) Config from yaml
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
