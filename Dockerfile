FROM openjdk:22-jdk-slim
COPY spring-eshop-0.0.1-SNAPSHOT.jar /usr/app/spring-eshop-0.0.1-SNAPSHOT.jar
ENV JAR_FILE=spring-eshop-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/usr/app/spring-eshop-0.0.1-SNAPSHOT.jar"]