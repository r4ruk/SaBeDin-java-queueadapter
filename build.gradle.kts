plugins {
    id("java")
    id("io.freefair.lombok") version "5.0.0"
}

group = "sabedin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.3.0")
//    testImplementation ("com.tngtech.archunit:archunit:1.3.0")
    implementation("com.rabbitmq:amqp-client:5.17.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2") // For Java 8 Date/Time API
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.2") // For parameter names module
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2") // For Kotlin support
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10") // If you're using Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10") // If you're using Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10") // If you're using Kotlin
    compileOnly ("org.projectlombok:lombok:1.18.34")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")

    testCompileOnly ("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor( "org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}