FROM maven:3.5.4-jdk-8-alpine

RUN mkdir -p /deploy/application/payslip

VOLUME ["/deploy/application/payslip"]

RUN mvn clean package

COPY ./target/payslip.jar /deploy/application/payslip/payslip.jar

WORKDIR /deploy/application/payslip

EXPOSE 8088

CMD ["java", "-jar", "payslip.jar"]
