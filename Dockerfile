FROM maven:3.5.4-jdk-8-alpine

RUN mkdir -p /deploy/application

VOLUME ["/deploy/application"]

WORKDIR /deploy/application

COPY . .

ENTRYPOINT ["mvn","clean","package"]