FROM maven:3.5.3-jdk-8-alpine AS build

WORKDIR /home/app

COPY pom.xml    /home/app

RUN mvn -f /home/app/pom.xml dependency:go-offline

COPY smoke.xml    /home/app

COPY src  /home/app/src

CMD tail -f /dev/null