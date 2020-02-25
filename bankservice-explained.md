# Running the application 

```bash
docker run -d shinojosa/bankjob:perform2020
```

> This command will pull the image from docker hub. You only need docker and an internet connection. The version downloaded is the version presented in [Perform 2020 in las Vegas](https://www.dynatrace.com/perform-vegas/).  



#  Developing and playing around with the application

This application was created with Eclipse and Maven. There is a `conf` directory. In there there you can play around with the properties of the application. 

## Import in Eclipse

The project can be imported with pretty much any version of [Eclipse](https://www.eclipse.org/). 

### Compiling

You can compile the application directly with eclipse or with the following maven command:

`mvn clean install`

the version of the JAR is defined in the `pom.xml` definition file. If you change the version, change also the Dockerfile to point ot the right version.

Create the Docker Image

`docker build `