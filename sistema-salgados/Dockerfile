# Estágio de Compilação: Usa o Maven para compilar o código
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução: Usa uma imagem leve do Java 17 para correr o sistema
FROM openjdk:17-jdk-slim
# Copia o jar gerado no estágio anterior para a raiz do container
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]