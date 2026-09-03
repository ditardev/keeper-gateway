FROM eclipse-temurin:21
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} GatewayApplication.jar
ENTRYPOINT ["java","-jar","GatewayApplication.jar"]
