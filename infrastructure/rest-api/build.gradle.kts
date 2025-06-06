plugins {
    id("org.openapi.generator")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:rest-api:ccas-openapi"))

    implementation(libs.springOauth2)
    implementation(libs.springBootWeb)
    implementation(libs.springSecurity)
    implementation(libs.bundles.swagger)
}

sourceSets {
    main {
        java {
            srcDirs(
                "src/main/java",
                "$buildDir/generated/src/main/java"
            )
        }
    }
}

openApiGenerate {
    inputSpec.set("$projectDir/ccas-openapi/src/main/resources/openapi.yaml")

    generatorName.set("spring")
    library.set("spring-boot")

    outputDir.set("$buildDir/generated")

    apiPackage.set("com.oleg.customer.costs.analytics.api")
    modelPackage.set("com.oleg.customer.costs.analytics.model")

    modelNameSuffix = "Dto"
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useTags"       to "true",
            "dateLibrary"   to "java8",
            "useSpringBoot3" to "true",
            "implicitHeaders" to "true",
            "openApiNullable" to "false",
            "modelNameSuffix"   to "Dto",
            "useBeanValidation" to "false",
            "useResponseEntity" to "false",
            "skipDefaultInterface" to "true",
            "annotationLibrary" to "swagger2",
            "hideGenerationTimestamp" to "true"
        )
    )
}

tasks.compileJava {
    dependsOn("openApiGenerate")
}