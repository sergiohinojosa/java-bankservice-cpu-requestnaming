# Understanding the Bankservice Application

## Running the application 

```bash
docker run -d shinojosa/bankjob:perform2020
```
> This command will pull the image from docker hub. You only need docker and an internet connection. The version downloaded is the version presented in [Perform 2020 in las Vegas](https://www.dynatrace.com/perform-vegas/).  


## Playing around with the application

This application was created with [Visual Code](https://code.visualstudio.com/) and [Maven](https://maven.apache.org/). There is a `conf` directory. In there there you can play around with the properties of the application. 

## The Bankjob.properties

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
Modify the properties and run the BankTask file to reflect the changes inside Visual Studio Code. You can also execute the file `sh buildjaranddocker.sh` to build it.

## The Application Structure

TBD

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
