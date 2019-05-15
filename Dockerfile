FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
WORKDIR /app
EXPOSE 8080
COPY applications/app-service/build/libs/applications-app-service-0.0.1.jar /app/app.jar
ENTRYPOINT exec java -Dfile.encoding=UTF-8 $JAVA_OPTS -jar /app/app.jar