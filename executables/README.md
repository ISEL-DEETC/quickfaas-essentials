# JAR executables

In order to execute QuickFaaS JAR files, you will first need to install JDK 11 (Java Development Kit) or later, which already includes a JRE (Java Runtime Environment) version.

## QuickFaaS-Auth-1.0-fat.jar
Run the JAR file on the command line or terminal window:
```
java -jar QuickFaaS-Auth-1.0-fat.jar
```
You will be prompt with several options of cloud providers to choose from. After, the application will try to redirect you to the provider's authentication webpage using your default browser. The authentication process relies on the OAuth 2.0 protocol. The application will never have access to your credentials. You will, however, be requested to grant access to a certain number of scopes that are required for the application to work as expected.

After a successful authentication, an access token will be provided. You will be asked for this token to be inserted in the `func-deployment.json` file to enable FaaS deployments.

In order to receive tokens sent by providers, the application launches an HTTP server that starts listening for requests to a callback API locally, on the `8080` port.

## QuickFaaS-Deployment-1.0-fat.jar
Before running the JAR file, make sure you have your cloud-agnostic function file ready, together with the `func-deployment.json` file configured. You can find templates for both of these files in the _[templates](https://github.com/Pexers/quickfaas-essentials/tree/main/templates)_ root directory.

The `./function-deployment` directory as well as the `func-deployment.json` file should be on the same directory as the JAR file.  
Run the JAR file on the command line or terminal window:
```
java -jar QuickFaaS-Deployment-1.0-fat.jar
```

The `func-deployment.json` file can be configured with the following values:
| JSON property | Values |
| --- | ----- |
| **cloudProvider** | `gcp`, `msazure`<br/>The chosen cloud provider for the FaaS deployment. |
| **accessToken** | The OAuth 2.0 access token provided by `QuickFaaS-Auth-1.0-fat.jar` after a successful authentication. |
| **subscriptionId**<br/>[MsAzure exclusive] | MsAzure active subscription ID. |
| **project** | The project responsible for holding deployed resources.<br/>GCP -> _Project_ name<br/>MsAzure -> _Resource Group_ name |
| **function.name** | The FaaS resource name.<br/>&ensp; |
| **function.location** | The location where the resource resides, preferably as close as possible<br/>to the end user. |
| **function.bucket** | GCP -> _Bucket_ name<br/>MsAzure -> _Storage Account_ name |
| **function.runtime** | `java11`<br/>The function's runtime. |
| **function.trigger.type** | `http`, `storage`<br/>The function's execution trigger. |
| **function.trigger.bucket**<br/>[storage trigger exclusive] | The bucket to detect changes.<br/>GCP -> _Bucket_ name<br/>MsAzure -> _Storage Account_ name |
| **function.trigger.eventType**<br/>[storage trigger exclusive] | `Create`, `Delete`, `Update`<br/>The storage event type to trigger execution. Only `Create` is supported in MsAzure for now. |
| **functionFile** | Path to the cloud-agnostic function definition file. |
| **dependenciesFile**<br/>[optional] | Path to the function's extra dependencies file to be installed before deployment. |
| **configurationsFile**<br/>[optional] | Path to the configurations JSON file. |
### Java functions
QuickFaaS uses Apache Maven to build Java projects before deployment. For now, the `./function-deployment` directory already comes with a Maven version, so that you don't need to install it separately. However, this may change in future releases.
