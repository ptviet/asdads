FROM openjdk:8-jre-alpine
ADD target/payslip.jar payslip.jar
ENTRYPOINT ["java", "-Xms32m", "-Xmx128m", "-Dspring.profiles.active=prod", "-jar", "/payslip.jar"]
EXPOSE 8088