FROM openjdk:8-alpine
LABEL maintainer="Seckin"
COPY /build/libs/morty-0.0.1-SNAPSHOT.jar /morty.jar
COPY morty.gif /morty.gif
COPY morty.png /morty.png
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "morty.jar"]