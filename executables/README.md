# JAR executables

In order to execute QuickFaaS JAR files, you will first need to install JDK 11 (Java Development Kit) or later, which already includes a JRE (Java Runtime Environment) version.

## _QuickFaaS-Auth-1.0-fat.jar_ usage
Run the JAR file on the command line or terminal window:
```
java -jar QuickFaaS-Auth-1.0-fat.jar
```
You will be prompt with several options of cloud providers to choose from. Once selected, the application will try to open a browser for you, where you will be able to authenticate in the chosen cloud provider. The authentication process relies on the OAuth 2.0 protocol. The application will never have access to the inserted credentials. You can also be requested to grant access to a certain number of scopes that required for the application to work properly.

## _QuickFaaS-Deployment-1.0-fat.jar_ usage
TODO.
### Java functions
QuickFaaS uses Apache Maven to build Java projects before deployment. For now, the `./function-deployment` directory already comes with a Maven version, so you won't need to install it separately. However, this may change in future releases.
