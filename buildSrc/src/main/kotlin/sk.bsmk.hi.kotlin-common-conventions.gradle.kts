plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.spring")
}

repositories {
    jcenter()
}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform("org.apache.camel.springboot:camel-spring-boot-bom:3.8.0"))
    implementation(platform("org.apache.camel:camel-bom:3.8.0"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("io.kotest:kotest-runner-junit5:4.4.3")
    testImplementation("org.apache.camel:camel-test-spring-junit5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}