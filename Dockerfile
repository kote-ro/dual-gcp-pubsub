FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY target/dual-pub-sub-1.0-SNAPSHOT.jar /app/dual-pub-sub-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/app/dual-pub-sub-1.0-SNAPSHOT.jar"]
