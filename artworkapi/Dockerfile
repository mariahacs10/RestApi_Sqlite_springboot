FROM openjdk:17-jdk AS build
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/artworkapi-0.0.1-SNAPSHOT.jar /app/artworkapi.jar

WORKDIR /app
ENV ANIMECON_API_KEY=5e09b23d-3571-4bd1-bda4-6e906f63c342
ENV ERIKA_ARTWORK_API_KEY=7a09e23d-3571-4bd1-bda4-9r906g62c132
# Ensure that Spring Boot connects to Render's PostgreSQL database
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "artworkapi.jar"]