# JAR executables

In order to execute QuickFaaS JAR files, you will first need to install JDK 11 (Java Development Kit) or later, which already includes a JRE (Java Runtime Environment) version.

## QuickFaaS-Auth-1.0-fat.jar
Run the JAR file on the command line or terminal window:
```
java -jar QuickFaaS-Auth-1.0-fat.jar
```
You will be prompt with several options of cloud providers to choose from. Once selected, the application will try to open a browser for you, where you will be able to authenticate in the chosen cloud provider. The authentication process relies on the OAuth 2.0 protocol. The application will never have access to the inserted credentials. You can also be requested to grant access to a certain number of scopes that required for the application to work properly.

After a successful authentication, an access token will be printed. You will be asked for this token to be inserted in the `func-deployment.json` file to enable FaaS deployments.

In order to receive tokens sent by providers, the application launches an HTTP server that starts listening for requests to a callback API locally, on the `8080` port.

## QuickFaaS-Deployment-1.0-fat.jar
Before running the JAR file, make sure you have your cloud-agnostic function file ready, together with the `func-deployment.json` file configured. You can find templates for both of these files in the _[templates](https://github.com/Pexers/quickfaas-essentials/tree/main/templates)_ root directory.

The `func-deployment.json` file can be configured with the following values:
| JSON property | Values |
| --- | ----- |
| **cloudProvider**<br/>&ensp; | `gcp`, `msazure`<br/>The chosen cloud provider for the FaaS deployment. |
| **accessToken**<br/>&ensp; | The OAuth 2.0 access token provided by `QuickFaaS-Auth-1.0-fat.jar`<br/>after a successful authentication. |
| **project**<br/>&ensp; | The project responsible for holding deployed resources<br/>GCP -> _Project_ name<br/>MsAzure -> _Resource Group_ name |
| **function.name**<br/>&ensp; | The FaaS resource name.<br/>&ensp; |
| **function.location**<br/>&ensp; | The location where the resource resides, preferably as close as possible<br/>to the end user. |
| **function.bucket**<br/>&ensp; | GCP -> _Bucket_ name<br/>MsAzure -> _Storage Account_ name |
| **function.runtime**<br/>&ensp; | `java11`<br/>The function's runtime. |
| **function.trigger.type**<br/>&ensp; | `http`, `storage`<br/>The function's execution trigger. |
| **function.trigger.bucket**<br/>[storage trigger exclusive] | The bucket to detect changes.<br/>GCP -> _Bucket_ name<br/>MsAzure -> _Storage Account_ name |
| **function.trigger.eventType**<br/>[storage trigger exclusive] | `Create`, `Delete`, `Update`<br/>The storage event type to trigger execution. Only `Create` is supported<br/>in MsAzure for now. |
| **functionFile**<br/>&ensp; | Path to the cloud-agnostic function definition file. |
| **dependenciesFile**<br/>[optional] | Path to the function's extra dependencies file to be installed before<br/>deployment. |
| **configurationsFile**<br/>[optional] | Path to the configurations JSON file. |

### Java functions
QuickFaaS uses Apache Maven to build Java projects before deployment. For now, the `./function-deployment` directory already comes with a Maven version, so you won't need to install it separately. However, this may change in future releases.
