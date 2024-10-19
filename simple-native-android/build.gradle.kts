import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
}

group = "io.github.sagemik"
version = "1.0.1"
description = "Simple 的 Android 原生库，用于 sqlite3_simple Flutter 插件"

android {
    namespace = "io.github.sagemik.simple_native_android"

    compileSdk = 34
    ndkVersion = "23.1.7779620"

    defaultConfig {
        minSdk = 21

        ndk {
            abiFilters += setOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    externalNativeBuild {
        cmake {
            version = "3.22.1"
            path = File("src/main/cpp/CMakeLists.txt")
        }
    }
}

mavenPublishing {
    publishToMavenCentral(
        host = SonatypeHost.CENTRAL_PORTAL,
        automaticRelease = false
    )

    signAllPublications()

    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            sourcesJar = true,
            publishJavadocJar = false
        )
    )

    coordinates(
        groupId = project.group.toString(),
        artifactId = project.name,
        version = project.version.toString()
    )

    pom {
        val userBaseUrl = "github.com/SageMik"
        val projectBaseUrl = "$userBaseUrl/${project.name}"

        name.set(project.name)
        description.set(project.description)
        url.set("https://$projectBaseUrl")

        developers {
            developer {
                id.set("sagemik")
                name.set("SageMik")
                email.set("sagemik@163.com")
                url.set("https://$userBaseUrl")

                println(url.get())
            }
        }

        licenses {
            license {
                name.set("MIT license")
                url.set("https://mit-license.org")
            }
        }

        scm {
            url.set("https://$projectBaseUrl")
            connection.set("scm:git:git://${projectBaseUrl}.git")
            developerConnection.set("scm:git:ssh://git@${projectBaseUrl}.git")
        }
    }
}
