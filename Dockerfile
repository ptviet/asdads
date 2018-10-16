FROM maven:3.5.4-jdk-8-alpine

RUN mkdir -p /deploy/application

VOLUME ["/deploy/application"]

WORKDIR /deploy/application

COPY ./target/payslip.jar ./payslip.jar

RUN mvn clean package

EXPOSE 8088

CMD ["java", "-jar", "payslip.jar"]
