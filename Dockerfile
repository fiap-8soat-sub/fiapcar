FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install

FROM openjdk:21-jdk
WORKDIR /app

ARG JAR_VERSION

RUN echo "Usando versão do JAR: ${JAR_VERSION}"

COPY --from=builder /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
