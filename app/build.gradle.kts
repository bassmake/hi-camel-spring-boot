plugins {
    id("sk.bsmk.hi.kotlin-application-conventions")
}

dependencies {
    implementation(project(":pipelines"))
}

application {
    mainClass.set("sk.bsmk.hi.app.AppKt")
}
