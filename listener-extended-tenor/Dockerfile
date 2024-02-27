FROM 127.0.0.1/base/openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY /config/java.security /opt/openjdk-17/conf/security/java.security 
COPY email/* email/
#ENTRYPOINT ["java","-jar","/app.jar"]

CMD java -jar /app.jar --spring.config.location=file:///config/application.properties -Duser.timezone="GMT+7"

ENV TZ="Asia/Jakarta"

EXPOSE 80