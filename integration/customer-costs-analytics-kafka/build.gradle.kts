plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":integration:customer-costs-analytics-reload"))

    implementation(libs.springKafka)
    implementation(libs.springAutoConfigure)
}


