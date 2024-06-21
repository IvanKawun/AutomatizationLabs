plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")

    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

tasks.register("checkFile") {
    doLast {
        val file = file("path/to/your/file.txt")
        if (file.exists()) {
            println("File exists")
        } else {
            println("File does not exist")
        }
    }
}

tasks.register<Zip>("customBuild") {
    from("module1")
    from("module2")
    from("module3")
    archiveFileName.set("customBuild.zip")
    destinationDirectory.set(file("$buildDir/distributions"))
}

tasks.named("build") {
    dependsOn("customBuild")
}

tasks {
    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE // Установим стратегию обработки дубликатов
    }
}
