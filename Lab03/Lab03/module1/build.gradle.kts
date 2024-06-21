plugins {
    `java-library`
}

dependencies {
    api("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.google.guava:guava:30.1-jre")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}