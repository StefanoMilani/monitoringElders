# Monitoring elders
IoT mini project

### Group components:

+ [Stefano Milani](https://www.linkedin.com/in/stefano-milani-561044181/)
+ [Fabio Di Spazio](https://www.linkedin.com/in/fabio-di-spazio-ba4697182/)
+ [Marco Ferraro](https://www.linkedin.com/in/marco-ferraro-86aa53182/)

### Professor:
+ [Ioannis Chatzigiannakis](https://www.linkedin.com/in/ichatz/)

The project aims to develop an useful application that can monitor elder people.
To make that posssible we use a set of technologies:
+ [Amazfit Bip](https://us.amazfit.com/shop/bip?variant=336750)
+ [Gadgetbridge](https://github.com/Freeyourgadget/Gadgetbridge)
+ [Apache Edgent](http://edgent.apache.org/#home)
+ [IBM Watson IoT Platform](https://internetofthings.ibmcloud.com)

#### Amazfit Bip

Amazfit Bip is a smartwatch that can be used to monitor the activity (ex: number of steps) and the heart rate of the elder.

| Pros                | Cons            |
|---------------------|-----------------|
|Battery life         |No blood pressure|                
|GPS                  |Activity depended|
|Heart Rate sensor    ||
|Multisport tracking  ||

Activity depended means that the smartwatch measures some data, such as heart rate, only if you start an activity. 

#### Gadgetbridge

Gadgetbridge is an open source android application which allows you to connect your smartphone to the Amazfit Bip without using the vendor application.
###### Pros:
* Open source 
* Compatible with several smart-watches 
* Implements most of the smartwatch functionalities 
* Updates continuously 
###### Cons:

* Support for Amazfit Bip not fully implemented

#### Apache Edgent

Apache edgent is a programming model and micro-kernel style runtime that can be embedded in gateways and other device.
It is used for edge computing, it can analyse continuous stream of data coming from the sensors and it can send thid data to the cloud.
It can be embedded in every device that support Java. 
Java 8 (lambda function), Java 7 and android (Java 6) support.
Retrolambda: transform Java 8 code in Java 7 or 6 code

#### IBM Watson IoT Platform

It is a fully managed, cloud-hosted service that is designed to simplify and derive the value from your IoT devices.
It provides a simple user interface where you can simply add and manage your devices, control access to your IoT service, and monitor your usage. 
Apache Edgent has simple APIs to communicate with IBM Watson IoT Platform.
It offers boards to visualize your data.

## Useful links

* [Concept and technology](https://www.slideshare.net/milanistef/monitoring-elders) (Some technology have changed due some issues found during implementation)
* [Proof of concept presentation](https://www.slideshare.net/milanistef/monitoring-elders-proof-of-concept)
* [Hackster post](https://www.hackster.io/151977/monitoring-elders-physical-state-remotely-with-iot-tech-c2da2e)



