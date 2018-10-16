FROM maven:3.5.4-jdk-8-alpine

WORKDIR /usr/app/payslip

COPY . .

RUN mvn clean package

WORKDIR /usr/app/payslip/target

EXPOSE 8088

CMD ["java", "-jar", "payslip.jar"]
