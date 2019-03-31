FROM java:8
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} yoda-web-services.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/yoda-web-services.jar"]