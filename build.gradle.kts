plugins {
    id("io.freefair.lombok") version "6.5.0.3"
    java
}

repositories {
    mavenLocal()
    maven("http://maven.elmakers.com/repository/") {
        isAllowInsecureProtocol = true
    }
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io")
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("org.bukkit:craftbukkit:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

group = "com.mcndsj"
version = "1.0-SNAPSHOT"
description = "TheWalls"
java.sourceCompatibility = JavaVersion.VERSION_1_8
