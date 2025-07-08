FROM maven:3.9.9-eclipse-temurin-22-jammy AS builder
WORKDIR /app

COPY . .
COPY settings.xml /root/.m2/settings.xml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jdk-jammy
WORKDIR /app

# Giả sử bạn muốn chạy command-service
COPY --from=builder /app/query-service/target/*.jar ./query-service.jar

ENTRYPOINT [ "java", "-jar", "query-service.jar" ]
EXPOSE 8082