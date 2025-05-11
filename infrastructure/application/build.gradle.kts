plugins {
    id("java")
    id("org.springframework.boot")
    id("com.google.cloud.tools.jib")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:rest-api"))
    implementation(project(":infrastructure:rest-api:ccas-openapi"))
    implementation(project(":integration:customer-costs-analytics-kafka"))
    implementation(project(":integration:customer-costs-analytics-reload"))
    implementation(project(":integration:customer-costs-analytics-database"))

    implementation(libs.springKafka)
    implementation(libs.bundles.jooq)
    implementation(libs.springBootWeb)
    implementation(libs.springBootJdbc)
    implementation(libs.springBootStarter)
}

tasks.jar {
    enabled = false
}

springBoot {
    buildInfo()
}

val dockerHubUsername: String by project
val dockerHubPassword = System.getenv("DOCKER_HUB_PASSWORD")
val imageVersion = System.getenv().getOrDefault("IMAGE_VERSION", "")

jib {
    from {
        image = "eclipse-temurin:21-jdk"
    }

    to {
        image = "molodoyboy777/customer-costs-analytics-service:${imageVersion}"

        auth {
            username = dockerHubUsername
            password = dockerHubPassword
        }
    }

    container {
        jvmFlags = listOf("-XX:MaxRAMPercentage=80")
        mainClass = "com.oleg.customer.costs.analytics.CustomerCostAnalyticServiceApplication"
    }
}