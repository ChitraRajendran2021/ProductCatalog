## Prerequisites

- **Java 17** (JDK)
- **Docker** (if running via Docker)
- **Maven** (for local builds, optional if using Docker)


### Move to the base directory
cd productcatalog


## Building the Application

### Dev Build:
mvn spring-boot:run


### Docker Build
docker build -t productcatalog .   //MAke Sure Docker deamon is up and running

**Run the Docker Container**:
   docker run -p 8080:8080 productcatalog

http://localhost:8080


