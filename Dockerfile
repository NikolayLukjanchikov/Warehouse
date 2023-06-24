FROM maven:3.8.4-openjdk-11-slim as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8881
ENTRYPOINT ["java", "-jar", "/app/*.jar"]