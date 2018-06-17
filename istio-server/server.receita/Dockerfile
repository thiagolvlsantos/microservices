FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/server.receita-0.0.1-SNAPSHOT.war /tmp/server-receita.war
ENTRYPOINT ["java","-jar","/tmp/server-receita.war"]
