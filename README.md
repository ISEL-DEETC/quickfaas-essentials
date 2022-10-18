# QuickFaaS Essentials ☁️

QuickFaaS is a multi-cloud interoperability desktop tool targeting cloud-agnostic functions development and FaaS deployments. QuickFaaS substantially improves developer’s productivity, flexibility and agility when creating serverless solutions to multiple cloud providers without requiring the installation of extra software. The proposed cloud-agnostic approach enables developers to reuse their serverless functions in multiple cloud providers, with the convenience of not having to change a single line of code. The solution aims to minimize vendor lock-in issues in FaaS platforms, and will, therefore, encourage developers and organizations to target different cloud providers in exchange for a functional benefit.

In terms of code development, this repository includes the uniform programming model for authentication and FaaS deployments, together with the cloud-agnostic libraries. We also provide an evaluation in the form of Excel spreadsheets to validate the proposed solution by measuring the impact of a cloud-agnostic approach on the function's performance, when compared to a cloud-non-agnostic one.

Be sure to check out the [_wiki_](https://github.com/Pexers/quickfaas-essentials/wiki) page for more information regarding the usage of cloud-agnostic libraries.

<p align="center">
  <img src="https://user-images.githubusercontent.com/47757441/185813592-ed461efa-2c40-4d43-9024-d2cf3fc13324.png" width="400">
</p>

## Directory structure
```
quickfaas-essentials
│   LICENSE -> apache License 2.0 
│   README.md -> this markup language file
│
└───QuickFaaS-Auth -> uniform programming model for OAuth 2.0
│                     authentication mechanism
│
└───QuickFaaS-Deployment -> uniform programming model for the deployment
│                           of cloud-agnostic FaaS applications
│   
└───evaluation
│   │   search-blobs -> use case designed exclusively for
│   │                   the purpose of performance testing
│   └───QuickFaaS-Metrics.xlsx -> performance metrics data in the
│                                 form of excel spreadsheets
│   
└───executables
│   │   function-deployment -> required resources for FaaS deployments
│   │   QuickFaaS-Auth-1.0-fat.jar -> authentication mechanism executable
│   └───QuickFaaS-Deployment-1.0-fat.jar -> FaaS deployment executable
│
└───libraries -> clou-agnostic libraries for the development
│                of serverless functions
|
└───templates -> cloud-agnostic function templates
```
