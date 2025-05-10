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
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(libs.bundles.jooq)
    implementation(libs.springBootJdbc)
}

tasks.jar {
    enabled = false
}

springBoot {
    buildInfo()
}

val imageVersion: String by project
val dockerHubUsername: String by project
val dockerHubPassword: String by project

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