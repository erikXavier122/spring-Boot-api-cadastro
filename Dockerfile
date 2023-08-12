FROM openjdk:17
VOLUME /tmp

# Execute o processo de construção do Spring Boot (substitua pelo comando real de construção)
RUN mvn clean package

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]