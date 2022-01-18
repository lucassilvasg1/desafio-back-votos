FROM openjdk:8

WORKDIR /app

COPY target/demo.jar /app/demo.jar

EXPOSE 8080

CMD ["java", "-jar", "demo.jar"]