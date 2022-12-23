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

## Publications 📰
#### _QuickFaaS: Providing Portability and Interoperability between FaaS Platforms_
- [Accepted for publication](https://doi.org/10.3390/fi14120360) by MDPI in the peer-reviewed scientific journal _Future Internet_, within the special issue "_Distributed Systems for Emerging Computing: Platform and Application_".
- [Included in the proceedings](https://link.springer.com/book/9783031232992) of the _9<sup>th</sup> European Conference On Service-Oriented And Cloud Computing_ (ESOCC), in the projects track, to be published by Springer in the _Communications in Computer and Information Science_ (CCIS) book series.

## Application screenshots 🖥️
Authentication|Function Configuration|
:-------------------------:|:-------------------------:|
<kbd><img src="https://user-images.githubusercontent.com/47757441/209371994-3bfa1416-dd7a-482c-8031-4897dedf9df0.png" width="400"></kbd>|<kbd><img src="https://user-images.githubusercontent.com/47757441/209371997-8713343a-1942-4a37-a21f-aa554723b99f.png" width="400"></kbd>|

Function Definition|FaaS Deployment|
:-------------------------:|:-------------------------:|
<kbd><img src="https://user-images.githubusercontent.com/47757441/209371999-e5dd8e98-824f-444f-9394-9c16a47279f7.png" width="400"></kbd>|<kbd><img src="https://user-images.githubusercontent.com/47757441/209372001-5b8109b7-f975-46ef-b351-1d106666c9f8.png" width="400"></kbd>|

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
