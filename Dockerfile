FROM maven:3.5.4-jdk-8-alpine

RUN mkdir -p /deploy/application

VOLUME ["/deploy/application"]

WORKDIR /deploy/application

RUN mvn clean package

COPY ./target/payslip.jar ./payslip.jar

EXPOSE 8088

CMD ["java", "-jar", "payslip.jar"]
