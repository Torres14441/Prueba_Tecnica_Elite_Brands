#Buildear la app
FROM eclipse-temurin:21-jdk AS builder

#indicador de directorio
WORKDIR /app
#copiar la aplicacion
COPY . .
#correr maven pero sin los test
RUN ./mvnw clean package -DskipTests

#correr la aplicación
FROM eclipse-temurin:21-jre
#setear el directorio
WORKDIR /app
#copiar el archivo jar
COPY --from=builder /app/target/*.jar app.jar
#Exponer el puerto en que se corre la app
EXPOSE 8080
#comando para correr la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]