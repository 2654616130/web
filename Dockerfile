FROM java
COPY target/web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+8", "app.jar"]