# Understanding the Bankservice Application

## Running the application 

```bash
docker run -d shinojosa/bankjob:perform2020
```
> This command will pull the image from docker hub. You only need docker and an internet connection. The version downloaded is the version presented in [Perform 2020 in las Vegas](https://www.dynatrace.com/perform-vegas/).  


## Playing around with the application

This application was created with [Visual Code](https://code.visualstudio.com/) and [Maven](https://maven.apache.org/). There is a `conf` directory. In there there you can play around with the properties of the application. 

## The Bankjob.properties

You can play around with the properties of the application changing the amount of threads, failure-rate, sleeptimes, etc..

```properties
# Urls to check
urls=https://blog.dynatrace.com,https://help.dynatrace.com,https://www.dynatrace.com,http://172.17.0.1:9080/easytravel/rest/journeys/recommendation/
# The Amount and Name of the Banks that execute Jobs
banks=Dynatrace Bank,ACME Bank,Swiss Bank,EasyTravel Bank
# The Failurerate of the Riskyjob, int values from 0 to 100
failurerate=50
# The Fibbonacci calculations from the heavyCalculatoin Job. It will do fibonnaci(random(min, max))
# Be carefull, this calculation is very imperformant.
minfibbonacci=10
maxfibbonacci=37
# The amount of Threads to start in the Background and asigned to ThreadGroups.
threads=25
# Maximal sleep time between job execution (its called with random) in s
sleeptime=6
```
After modifying the properties you have to run the BankTask again so the properties are loaded. You can run this also in debug mode from within Visual Studio Code. Or better yet,  execute the file `sh buildjaranddocker.sh` to compile it and pack it in the docker container.

## The Application Structure

```bash
├── Dockerfile						Dockerfile for creating the docker image	
├── *.sh							Shell helper files for building the image
├── conf
│   ├── bankjob.properties			BankJobs configuration properties
│   └── logger.properties			Logger configurations
├── doc							Folder for documentation 	
├── log							Folder with the application logs   
├── pom.xml						Maven configuration	
├── src
│   └── main
│      └── java
│          └── com
│              └── dynatrace
│                  └── se
│                      └── bankjob
│                          ├── business				Folder with custom exceptions
│                          ├── data				
│                          │   └── BankData.java		Singleton for holding initialization Data
│                          ├── fibonacci
│                          │   └── Fibonacci.java		Class for calculating Fibonacci
│                          ├── runner
│                          │   ├── BankTask.java		Main Class that starts the Threads in the ThreadGroups.
│                          │   └── BankThread.java		BankThread Definition
│                          └── util
│                              ├── Helper.java			Helper Class
│                              └── Job.java			Job Types (Enumeration)
│
│
└── target		Directory where compiled classes and libraries will land
```



## Open in Visual Studio Code 

For a better experience I recommend developing (if in Windows) with [WSL](https://docs.microsoft.com/en-us/windows/wsl/install-win10) and Visual Code connected as remote extension on the local machine. Go to your WSL bash terminal, clone the repository and open visual code:

```bash
git clone https://github.com/sergiohinojosa/java-bankservice-cpu-requestnaming
cd java-bankservice-cpu-requestnaming
code .
```

### Compiling

You can compile the application directly with eclipse or with the following maven command:

```bash
mvn clean install
```
or 
```bash
sh buildjaranddocker.sh
```
which compiles it and creates the docker image. You need the Docker service running in the Host. To connect to the Docker Daemo from WSL [here is a tutorial](https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly)
