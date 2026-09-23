FROM eclipse-temurin:24-jre
WORKDIR /app
COPY target/projetSoutenance2026PME-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar" ,"app.jar"]

