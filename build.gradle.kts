import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application

    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"

    id("com.github.johnrengelman.shadow") version "8.1.1"

    id("io.papermc.paperweight.userdev") version "1.7.3"

    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "yv.tils"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    paperweight.paperDevBundle("1.20-R0.1-SNAPSHOT")

    implementation("dev.jorel", "commandapi-bukkit-shade", "9.5.3")
    implementation("dev.jorel", "commandapi-bukkit-kotlin", "9.5.3")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    runServer {
        minecraftVersion("1.20")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}

application {
    mainClass.set("YVtils")
}