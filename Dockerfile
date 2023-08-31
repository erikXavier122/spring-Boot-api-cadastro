FROM openjdk:8-jdk-buster as build

RUN apt update && apt upgrade -y && apt install -y maven && \
    apt remove --auto-remove python -y && apt-get purge python -y && \
    apt-get update

COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY . .
RUN mvn --version
RUN mvn clean package -DskipTests -X

FROM openjdk:18-jdk-buster
RUN apt update && apt upgrade -y && apt install -y locales && \
    apt remove --auto-remove python -y && apt-get purge python -y && \
    apt-get update
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY --from=build /target/*.jar  /app.jar
RUN ls -lha
EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
