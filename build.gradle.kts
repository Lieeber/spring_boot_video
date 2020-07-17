import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com.lieeber.imoocvideo"
version = "0.0.1-SNAPSHOT"
description = "imoocvideo"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("http://repo.maven.apache.org/maven2")
    }
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.3.1.RELEASE")
    implementation("org.projectlombok:lombok:1.16.8")
    implementation("tk.mybatis:mapper-spring-boot-starter:2.1.5")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.2.3")
    implementation("commons-codec:commons-codec:1.11")
    implementation("org.apache.commons:commons-lang3:3.4")
    implementation("org.apache.commons:commons-io:1.3.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.3.1.RELEASE")
    implementation("redis.clients:jedis:3.3.0")
    implementation("org.springframework.data:spring-data-redis:2.3.1.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    runtimeOnly ("mysql:mysql-connector-java:8.0.20")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<JavaCompile> {
    options.encoding ="UTF-8"
}
