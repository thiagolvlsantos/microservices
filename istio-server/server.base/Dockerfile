FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/server.base-0.0.1-SNAPSHOT.war /tmp/server-base.war
ENTRYPOINT ["java","-jar","/tmp/server-base.war"]
