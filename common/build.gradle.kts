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
    implementation("io.cucumber:tag-expressions:4.1.0")
    
    intellijPlatform {
        intellijIdea(versions["intellij-version"]!!)
        plugin("Gherkin:${versions["gherkin"]}")
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
