FROM openjdk:17
EXPOSE 8090
ARG JAR_FILE_PATH=jar/bumpy-back-*-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} bumpy.jar
ENTRYPOINT ["java", "-jar", "bumpy.jar"]