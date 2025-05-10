plugins {
    id("java")
}

subprojects {
    apply(plugin = "java")

    group = "com.oleg.customercostsanalytics"

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor = JvmVendorSpec.ADOPTIUM
        }
    }
}

repositories {
    mavenCentral()
}
