FROM openjdk:17-oracle
COPY target/*.jar musicapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","musicapp.jar"]
