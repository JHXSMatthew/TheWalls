plugins {
    id("io.freefair.lombok") version "8.13.1"
    java
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.tabooproject.org/repository/releases/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT") {
        // As it's no longer available in spigot repository
        exclude("net.md-5","bungeecord-chat")
    }
    implementation("ink.ptms.core:v10800:10800")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

group = "com.mcndsj"
version = "1.0-SNAPSHOT"
description = "TheWalls"
java.sourceCompatibility = JavaVersion.VERSION_1_8
