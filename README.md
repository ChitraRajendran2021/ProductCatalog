## Prerequisites

- **Java 17** (JDK)
- **Docker** (if running via Docker)
- **Maven** (for local builds, optional if using Docker)

## STEP1 : Clone the repository:
git clone git@github.com:ChitraRajendran2021/ProductCatalog.git

### STEP2 : Move to the base directory
cd productcatalog


## Building the Application

### STEP3 :Local Build:
mvn spring-boot:run

## OR

### STEP3 :Docker Build
docker build -t productcatalog .   //MAke Sure Docker deamon is up and running

## STEP4 :Run the Docker Container**:
   docker run -p 8080:8080 productcatalog

http://localhost:8080


