# Use uma imagem do OpenJDK como base para construir o projeto
FROM openjdk:16-jdk as build

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie os arquivos de origem para o contêiner
COPY . .

# Execute o processo de construção do Spring Boot (substitua pelo comando real de construção)
RUN ./mvnw clean package

# Agora crie uma imagem mais leve para a execução
FROM openjdk:16-jdk-slim

# Diretório de trabalho dentro do contêiner de execução
WORKDIR /app

# Copie o arquivo JAR gerado durante a fase de construção para o contêiner de execução
COPY --from=build /app/target/seu-projeto-spring-boot.jar /app/app.jar

# Comando para executar o aplicativo Spring Boot
CMD ["java", "-jar", "app.jar"]