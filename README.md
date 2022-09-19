# QuickFaaS Essentials ☁️

QuickFaaS is a multi-cloud interoperability desktop tool targeting cloud-agnostic functions development and FaaS deployments. QuickFaaS substantially improves developer’s productivity, flexibility and agility when creating serverless solutions to multiple cloud providers without requiring the installation of extra software. The proposed cloud-agnostic approach enables developers to reuse their serverless functions in different cloud providers with no need to change a single line of code. 

The proposed solution aims to minimize vendor lock-in in FaaS platforms by increasing the portability of serverless functions, which will, therefore, encourage developers and organizations to target different providers in exchange for a functional benefit.

The current repo provides the essential contributions of this project. This includes the uniform programming model for authentication and deployment of cloud-agnostic functions, together with the cloud-agnostic libraries.

Be sure to check out the [*wiki*](https://github.com/Pexers/quickfaas-essentials/wiki) page for more information regarding cloud-agnostic libraries usage.

<p align="center">
  <img src="https://user-images.githubusercontent.com/47757441/185813592-ed461efa-2c40-4d43-9024-d2cf3fc13324.png" width="400">
</p>

## Directory structure
```
quickfaas-essentials
│   LICENSE -> apache License 2.0 
│   README.md -> this markup language file
│
└───Libraries -> clou-agnostic libraries for the development
│                of serverless functions
│
└───QuickFaaS-Auth -> uniform programming model for OAuth2.0
│                     authentication mechanism
│
└───QuickFaaS-Deployment -> uniform programming model for the deployment
│                           of cloud-agnostic FaaS applications
│   
└───evaluation -> performance metrics in the form of excel spreadsheets
│                 that validate the proposed cloud-agnostic approach
│   
└───executables
│   │   function-deployment -> required resources for FaaS deployments
│   │   QuickFaaS-Auth-1.0-fat.jar -> authentication mechanism executable
│   └───QuickFaaS-Deployment-1.0-fat.jar -> FaaS deployment executable
|
└───templates -> cloud-agnostic function templates
```
