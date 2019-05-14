FROM anapsix/alpine-java:8
WORKDIR /app
COPY build/libs/app-service-0.0.1.jar /app/app.jar
EXPOSE 8080
CMD java -jar /app/app.jar