plugins {
	val kotlinVersion = "2.2.21"

	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion

	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.micro"
version = "0.0.1"
description = "keeper-core-gateway"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//Eureka
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.2.1")

	//Gateway
	implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.2.2")

	implementation("com.github.loki4j:loki-logback-appender:1.5.2")

	//Addiction
	implementation("io.micrometer:micrometer-registry-prometheus:1.14.5")
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.4.4")
	implementation("org.springframework.boot:spring-boot-devtools:3.4.4")
	implementation("org.springframework.boot:spring-boot-configuration-processor:3.4.4")
	runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.110.Final:osx-aarch_64")
	compileOnly("org.projectlombok:lombok:1.18.38")
	implementation(kotlin("script-runtime"))
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks {
	jar {
		enabled = false
	}
}

tasks.withType<ProcessResources> {
	inputs.property("version", project.version) // Помогает Gradle кэшировать задачу

	filesMatching("**/application.yml") {
		filter { line ->
			line.replace("\${projectVersion}", project.version.toString())
		}
	}
}
