FROM alpine:3.13.5
RUN apk update && apk add openjdk11
WORKDIR /home/app
COPY target/weather-*.jar /home/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
