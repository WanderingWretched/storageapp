FROM openjdk:17
ADD build/libs/storage-app-0.0.1-SNAPSHOT.jar storageapp.jar
ENTRYPOINT ["java", "-jar", "/storageapp.jar"]