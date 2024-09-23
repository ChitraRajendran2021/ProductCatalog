FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven && apt-get clean

WORKDIR /app

COPY pom.xml . 
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=build /app/target/ProductCatalog-1.0-SNAPSHOT.jar productcatalog.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "productcatalog.jar"]
