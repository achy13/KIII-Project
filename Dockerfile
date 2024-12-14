FROM openjdk:17-jdk
COPY target/secureLogin.jar .
EXPOSE 8088
CMD ["java", "-jar", "secureLogin.jar"]