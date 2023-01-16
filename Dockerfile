FROM eclipse-temurin:17.0.5_8-jdk-alpine

WORKDIR /home

COPY . .
RUN ./gradlew clean bootJar

WORKDIR /home/build/libs
RUN cp *.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
