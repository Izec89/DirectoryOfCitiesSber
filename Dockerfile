FROM bellsoft/liberica-openjdk-alpine:8u302
WORKDIR /opt/app
ADD /target/City2-1.0-SNAPSHOT.jar City.jar

