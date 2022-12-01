FROM adoptopenjdk/openjdk16
VOLUME /home/storage
ARG JAR_FILE=/target/contact-list-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]