FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/billing_service-0.0.1.jar billing_service-0.0.1.jar
ENTRYPOINT ["java","-jar","/billing_service-0.0.1.jar"]