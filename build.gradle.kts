plugins {
    id("java")
}

subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor = JvmVendorSpec.ADOPTIUM
        }
    }

    group = "com.oleg.customercostsanalytics"

    val springBootVersion: String by project

    if (name !in setOf("ccas-openapi")) {
        dependencies {
            implementation(platform("org.springframework.boot:spring-boot-dependencies:${springBootVersion}"))
        }
    }
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.6"
    distributionType = Wrapper.DistributionType.ALL
}
