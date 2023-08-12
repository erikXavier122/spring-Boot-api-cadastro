FROM eclipse-temurin:17-jdk
VOLUME /tmp

# Execute o processo de construção do Spring Boot (substitua pelo comando real de construção)
RUN mvnw clean package

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]