# Use an official Maven image as a parent image
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the Maven project
RUN mvn clean package -Dmaven.test.skip=true

# Use a lightweight Java Runtime image as the final image
FROM openjdk:17-ea-16-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the builder image to the final image
COPY --from=builder /app/target/clustered-data-warehouse-0.0.1-SNAPSHOT.jar .

# Expose the port
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "/app/clustered-data-warehouse-0.0.1-SNAPSHOT.jar"]
