FROM openjdk:17-jdk AS build
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/artworkapi-0.0.1-SNAPSHOT.jar /app/artworkapi.jar

VOLUME /app/sqlite-data

WORKDIR /app
ENTRYPOINT ["java", "-jar", "artworkapi.jar"]