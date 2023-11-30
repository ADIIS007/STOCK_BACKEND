FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar backend-stock-project.jar
ENTRYPOINT ["java","-jar","/backend-stock-project.jar"]
EXPOSE 5000