FROM eclipse-temurin:17

LABEL autor=Marco-Vargas

COPY target/tallerApiSpringBoot-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]