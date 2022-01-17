FROM openjdk:11-jre-slim

COPY target/bankapp-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java","-jar","/server.jar"]

EXPOSE 8080