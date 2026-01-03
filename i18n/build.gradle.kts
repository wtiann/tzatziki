plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
    id("org.jetbrains.intellij.platform.module") version "2.10.4"
}

val versions: Map<String, String> by rootProject.extra

repositories {
    mavenCentral()
    
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("javazoom:jlayer:1.0.1")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("org.unbescape:unbescape:1.1.6.RELEASE")
    implementation("org.apache.commons:commons-csv:1.10.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("commons-codec:commons-codec:1.15")
    
    intellijPlatform {
        intellijIdea(versions["intellij-version"]!!)
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
    
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }
    
    jar {
        archiveBaseName.set(rootProject.name + "-" + project.name)
    }
}
