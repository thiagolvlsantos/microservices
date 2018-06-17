FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/server.pessoa-0.0.1-SNAPSHOT.war /tmp/server-pessoa.war
ENTRYPOINT ["java","-jar","/tmp/server-pessoa.war"]
