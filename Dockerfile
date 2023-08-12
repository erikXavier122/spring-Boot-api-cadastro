FROM openjdk:17-jdk-buster

RUN apt update && apt install -y maven

COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:17-jdk-buster
RUN apt update && apt install -y locales
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY --from=0 /target/*.jar  /app.jar

EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]