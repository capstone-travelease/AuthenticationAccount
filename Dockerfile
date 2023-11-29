FROM openjdk:21

VOLUME /tmp

COPY target/*.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 1234
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]