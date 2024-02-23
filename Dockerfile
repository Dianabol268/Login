FROM openjdk:20-jdk-oracle

WORKDIR /app

COPY ./target/login-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "login-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082
