plugins {
    kotlin("jvm") version "1.6.10"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

group = "io.github.prettykim"
version = "1.0"

repositories {
    mavenCentral()

    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))

    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
}
