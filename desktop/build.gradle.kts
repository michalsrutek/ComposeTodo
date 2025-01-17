plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("license")
}

kotlin.jvmToolchain(11)

dependencies {
    implementation(projects.composeClients)

    implementation(libs.ktor.client.cio)
    implementation(libs.sqldelight.sqliteDriver)
    implementation(compose.desktop.currentOs)
    implementation(libs.coroutines.swing)

    testImplementation(kotlin("test"))
}

compose.desktop {
    application {
        mainClass = "app.softwork.composetodo.MainKt"
    }
}

licensee {
    allow("MIT")
}

compose {
    kotlinCompilerPlugin.set("1.5.7.1")
}
