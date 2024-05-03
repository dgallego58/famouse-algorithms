plugins {
    id("java")
    id("me.champeau.jmh") version "0.7.2"
    id("idea")

}

group = "co.com.dgallego58"
version = "1.0.0"

repositories {
    mavenCentral()
}


idea{
    module{
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.openjdk.jmh:jmh-core:1.37")
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")


}

tasks.test {
    useJUnitPlatform()
}
