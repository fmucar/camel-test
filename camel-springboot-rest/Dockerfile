FROM openjdk:8-jdk-alpine

MAINTAINER Fatih Mehmet UCAR - fmucar@cooldatasoft.com

VOLUME /tmp

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

RUN apk add --no-cache bash
# RUN apk update && apk add bash

ENTRYPOINT ["java","-cp","app:app/lib/*","com.cooldatasoft.Application"]
