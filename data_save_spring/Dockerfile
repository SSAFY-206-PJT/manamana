FROM openjdk:11-jdk

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY ./crawling/build/libs/*.jar ./crawling-application.jar

COPY ./crawling-resources ./resources

EXPOSE 8081

ENTRYPOINT ["java","-jar","-Dspring.config.location=crawling-resources/application.yml,crawling-resources/application-deploy.yml", "-Dspring.profiles.active=deploy", "crawling-application.jar"]
