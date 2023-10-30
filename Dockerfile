FROM amazoncorretto:17.0.9
LABEL authors="Marcin Pawlicki"

ARG VERSION=0.0.1
ARG JAR_FILE=target/streaming-poc-rest-api-${VERSION}.jar
COPY ${JAR_FILE} streaming-poc-rest-api.jar

ENTRYPOINT ["java", "-jar", "/streaming-poc-rest-api.jar"]
