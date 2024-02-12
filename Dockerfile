# Build stage
FROM adoptopenjdk/openjdk11 as build
WORKDIR /app
COPY . .
RUN sed -i 's/\r$//' ./gradlew
RUN ./gradlew bootJar

# Runtime stage
FROM adoptopenjdk/openjdk11
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Set the working directory and expose port
EXPOSE 80

# Set the command to run the application with custom configuration
ENTRYPOINT ["java", "-Xmx256m", "-Xms256m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/dumps", "-XX:+PrintGCDetails", "-Xlog:gc*:file=/logs/gc.log:time:filesize=50M", "-jar", "-Dspring.profiles.active=local","/app/app.jar"]
