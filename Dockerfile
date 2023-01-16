FROM amazoncorretto:17

CMD ["java","-jar","build/libs/Chal-Chal-Server-0.0.1-SNAPSHOT.jar"]
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]