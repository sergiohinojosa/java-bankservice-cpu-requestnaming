# Enhancing and customizing the service detection via API

This exercise was not handled in the sessions since we did not have time left. But it is just a POST request via our API. If you notice the BankService, there is a jobtype that calls URLs (Check URL). Some calls go to the Dynatrace domain from different subdomains. You can expose this services manually when extracting this from *calls to public networks*

This is a manual task and manual tasks are boring (we don't like manual tasks), hence via API you can tell dynatrace to split these subdomains so every subdomain will be categorized in a separate service. 

If you want to learn more about customizing service detection via API please read the following post:

https://notes.lab.dynatrace.org/enhancing-and-customizing-the-dynatrace-service-detection/#public-network-subdomains

## Enhance the detection of the Dynatrace public subdomains

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

Now go to the bash terminal or GIT Terminal (you need to have curl installed) and export the following variables (Replace the values with your tenant and api-token)

```bash
export TENANT=https://yourtenant.live.dynatrace.com/
export APITOKEN=YOUR-API-TOKEN
```

Do a curl post request with the JSON payload
```bash
curl -X POST -H "Content-Type: application/json" -H "Authorization: Api-Token $APITOKEN" -d @myapirule.json $TENANT/api/config/v1/service/detectionRules/OPAQUE_AND_EXTERNAL_WEB_REQUEST

```

And that's it! this way you can redefine and keep track of requests to specific subdomains, subnetworks, network-ranges, etc... 