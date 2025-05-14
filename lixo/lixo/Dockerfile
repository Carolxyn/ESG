FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

# Copiar maven wrapper e POM (manter as camadas do cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Corrigir permissões e configurar encoding UTF-8
RUN chmod +x ./mvnw
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

# Para manter camadas de dependências no cache, instalamos as dependências
RUN ./mvnw dependency:go-offline -B

# Copiar o código-fonte
COPY src src

# Corrigir codificação dos arquivos de propriedades
RUN find /workspace/app/src -name "*.properties" -type f -exec sh -c 'iconv -f ISO-8859-1 -t UTF-8 {} > {}.utf8 && mv {}.utf8 {}' \;

# Compilar a aplicação
RUN ./mvnw package -DskipTests -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]