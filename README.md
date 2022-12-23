# QuickFaaS Essentials ☁️

QuickFaaS is a multi-cloud interoperability desktop tool targeting cloud-agnostic functions development and FaaS deployments. QuickFaaS substantially improves developers' productivity, flexibility and agility when creating serverless solutions to multiple cloud providers, **without requiring the installation of provider-specific software**. The proposed cloud-agnostic approach enables developers to reuse their serverless functions in different cloud providers with **no need to rewrite** code. The solution aims to **minimize vendor lock-in** in FaaS platforms by increasing the portability of serverless functions, which will, therefore, encourage developers and organizations to target different providers in exchange for a functional benefit.

<p align="center">
  <img src="https://user-images.githubusercontent.com/47757441/185813592-ed461efa-2c40-4d43-9024-d2cf3fc13324.png" width="360">
</p>

> **Note**  
> - QuickFaaS was originally developed within the context of a Master’s degree dissertation titled "_Characterizing and Providing Interoperability to
FaaS Platforms_", at Instituto Superior de Engenharia de Lisboa (ISEL), Lisboa, Portugal.

In terms of code development, this repository includes the uniform programming model for authentication and FaaS deployments, together with the cloud-agnostic libraries. We also provide an evaluation in the form of Excel spreadsheets to validate the proposed solution by measuring the impact of a cloud-agnostic approach on the function's performance, when compared to a cloud-non-agnostic one.

Be sure to check out the [_wiki_](https://github.com/Pexers/quickfaas-essentials/wiki) page for more information regarding the usage of cloud-agnostic libraries.

## Publications
### _QuickFaaS: Providing Portability and Interoperability between FaaS Platforms_
- Accepted for publication by MDPI in the peer-reviewed scientific journal Future Internet, within the special issue "_Distributed Systems for Emerging Computing: Platform and Application_" - [DOI](https://doi.org/10.3390/fi14120360)

## Application screenshots 🖥️
Authentication|Function Configuration|
:-------------------------:|:-------------------------:|
<img src="https://user-images.githubusercontent.com/47757441/205137934-11ec91ac-b44b-4f80-9d4f-4297a94ce34a.PNG" width="400">|<img src="https://user-images.githubusercontent.com/47757441/205140141-8e12eebd-2e0d-473b-bc2c-553c929e125a.PNG" width="400">|

Function Definition|FaaS Deployment|
:-------------------------:|:-------------------------:|
<img src="https://user-images.githubusercontent.com/47757441/205137982-ffd7c199-7cd3-4f50-a8d3-ad6607cd2378.PNG" width="400">|<img src="https://user-images.githubusercontent.com/47757441/205138397-35c2221c-f851-4f5f-8b8c-18bf68b682e8.PNG" width="370">|

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
