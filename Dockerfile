FROM maven:3.5.4-jdk-8-alpine
ADD target/payslip.jar payslip.jar
ENTRYPOINT ["java", "-Xms32m", "-Xmx128m", "-Dspring.profiles.active=prod", "-jar", "/payslip.jar"]
EXPOSE 8081
