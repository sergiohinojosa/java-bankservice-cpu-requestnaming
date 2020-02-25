# The Bankservice

With this small tutorial we will get a better understanding on how Davis baselines all the transactions in your environment. This is **key** for **automatic Root Cause analysis**. 

Davis does a tremendous job doing this with zero configuration. Nevertheless here is a specific small application that deviates from standards and executes small Jobs from multiple threads running in the background. With a little bit of configuration and human intuition we'll be able to **help Davis help us!**

![](doc/davis_knows.png)

## About the Bankservice application

This is a simple java application that can run as a standalone JVM or inside a docker container. This application executes small task in the background from different threads placed in different thread groups. The idea of this application is for educational purposes only. With Dynatrace you'll create a custom service to expose all transactions and then rename the transactions so Davis can calculate automatically the hotspots and will baseline every single Job-type. With this small configuration, Dynatrace will automatically keep track of all different types of jobs (even when executed in the background) and you can then go to sleep peacefully without having to configure any thresholds nor alerts and if something fails, Davis will let you know, automatically 🤩. **So, let's help Davis helps us**

## Running it

```bash
docker run -d shinojosa/bankjob:v0.2
```

> This command will pull the image from docker hub. You only need docker and an internet connection. 

## Doing the exercise

This application was made for an exercise presented on the EMEA Bootcamp. The principles and the hands-on exercise is presented in the following powerpoint presentation:

### [Request naming and Custom Services (Slides)](https://dynatrace.sharepoint.com/:p:/s/Sales/EMEA/EfU8seuo31BEnEwM80dMD-4BsBQEKBSV_WL23KbXrGeN2Q?e=RDZTGS)

#### Excurs

#### *Enhancing and customizing the service detection via API* 
This exercise was not handled in the sessions since we did not have time left. But it is just a POST request via our API. If you notice the BankService, there is a jobtype that calls URLs. All are from dynatrace and from different subdomains. Here is how you can split the different subdomains in different services so Dynatrace keeps track of the different services.
https://notes.lab.dynatrace.org/enhancing-and-customizing-the-dynatrace-service-detection/#public-network-subdomains

save the following json as `myapirule.json` file
 ```json
 {
     "name": "Dynatrace.com",
     "type": "OPAQUE_AND_EXTERNAL_WEB_REQUEST",
     "description": "",
     "enabled": true,
     "conditions": [
         {
             "attributeType": "TOP_LEVEL_DOMAIN",
             "compareOperations": [
                 {
                     "type": "ENDS_WITH",
                     "negate": false,
                     "ignoreCase": "true",
                     "values": [
                         "dynatrace.com"
                     ]
                 }
             ]
         }
     ],
     "publicDomainName": {
         "copyFromHostName": true
     },
     "port": {
         "doNotUseForServiceId": true
     }
 }
 ```
Now go to the GIT Terminal (since you have curl there installed) and export the following variables (Replace the values with your tenant and api-token)
```
export TENANT=https://vxt526.managed-sprint.dynalabs.io/e/sergio-hinojosa/
export TOKEN=2-AHF3jOTsOe_XxMA7RYj
```
Do a curl post request with the JSON payload.
`curl -X POST -H "Content-Type: application/json" -H "Authorization: Api-Token $TOKEN" -d @myapirule.json $TENANT/api/config/v1/service/detectionRules/OPAQUE_AND_EXTERNAL_WEB_REQUEST`

## Prerequisites

- Docker
- A Dynatrace tenant

## Developing and playing around with the application

This application was created with Eclipse and Maven. There is a `conf` directory. In there there you can play around with the properties of the application. 

### Import in Eclipse

The project can be imported with pretty much any version of [Eclipse](https://www.eclipse.org/). 

### Compiling

You can compile the application directly with eclipse or with the following maven command:

`mvn clean install`


the version of the JAR is defined in the `pom.xml` definition file. If you change the version, change also the Dockerfile to point ot the right version.

Create the Docker Image

`docker build `

## Author 

sergio.hinojosa@dynatrace.com
