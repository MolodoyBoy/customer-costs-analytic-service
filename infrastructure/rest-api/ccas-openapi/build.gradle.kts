plugins {
    id("java-library")
    id("maven-publish")
}

val customerCostsAnalyticOpenApi: String by project
version = customerCostsAnalyticOpenApi

val gitHubUsername: String by project
val gitHubToken = System.getenv("GPR_TOKEN")

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = project.name
            groupId = project.group.toString()
            version = project.version.toString()

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "OpenApiPackage"
            url  = uri("https://maven.pkg.github.com/MolodoyBoy/customer-costs-analytic-service")

            credentials {
                username = gitHubUsername
                password = gitHubToken
            }
        }
    }
}