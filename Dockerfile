FROM java:8
WORKDIR /
ADD embl-0.0.1-SNAPSHOT.jar embl-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","embl-0.0.1-SNAPSHOT.jar"]
