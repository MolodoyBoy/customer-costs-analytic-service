dependencies {
    implementation(project(":domain"))
    implementation(project(":integration:customer-costs-analytics-reload"))

    implementation(libs.springKafka)
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
}


