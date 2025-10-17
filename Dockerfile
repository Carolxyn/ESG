# Build stage
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build da aplicação
RUN apk add --no-cache maven && \
    mvn clean package -DskipTests && \
    apk del maven

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar o JAR do build stage
COPY --from=build /app/target/*.jar app.jar

# Expor a porta
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]