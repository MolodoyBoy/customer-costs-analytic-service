plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.flywayCore)
    implementation(libs.postrgesql)
    implementation(libs.bundles.jooq)
    implementation(libs.flywayPostgres)
    implementation(libs.springBootJdbc)
}


