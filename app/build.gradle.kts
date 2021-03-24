plugins {
    id("sk.bsmk.hi.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.camel.springboot:camel-spring-boot-starter")
    implementation("org.apache.camel.springboot:camel-http-starter")
    implementation("org.apache.camel.springboot:camel-timer-starter")

    implementation("org.apache.camel:camel-endpointdsl")
    implementation(project(":pipelines"))
    implementation(project(":processors"))
}

application {
    mainClass.set("sk.bsmk.hi.app.AppKt")
}
