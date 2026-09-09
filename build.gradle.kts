plugins {
    id("java")
}

group = "edu.nd.oose.hw1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveFileName.set("Apportionment.jar")

    manifest {
        attributes["Main-Class"] = "edu.nd.oose.hw1.Main"
    }
}