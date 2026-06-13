# Estágio de Compilação
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução 
FROM eclipse-temurin:17-jre-jammy
# Copia o jar gerado no estágio anterior para o container
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]