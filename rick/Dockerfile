FROM openjdk:8-alpine
LABEL maintainer="Seckin"
COPY /build/libs/rick-0.0.1-SNAPSHOT.jar /rick.jar
COPY rick.png /rick.png
COPY rick_pickle.gif /rick_pickle.gif
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "rick.jar"]