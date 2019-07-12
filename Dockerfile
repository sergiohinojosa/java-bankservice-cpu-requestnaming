# base image
FROM java:8-jdk-alpine
# create a directory for the app
WORKDIR /opt/bankjob
# the files to copy
COPY target/lib ./lib
COPY target/conf ./conf
COPY target/bankjob-0.0.1-SNAPSHOT.jar ./
# not exposing any port with EXPOSE 
CMD ["java", "-jar", "bankjob-0.0.1-SNAPSHOT.jar"]


