# sales-notification-app
sales-notification-app is a small message processing application that processes incoming notification messages from .json file(can be
extended to restfull service end point).

## Steps to configure and run the application. ##

Step 1: 2 Sample notification messages input files are placed under src/main/resources directory.
```
    notificatoins.json -- contains 51 messages of Type 1 message format.
    mixednotifications.json -- contains all three types of messages (Basic, Detailed, Adjustment).
```
     
Step 2: Test class : TestNotificationTestCase.java under folder : src/test/java/com/notification/testcases contains 2 testcases to
load above json files.
Before running test cases edit following configurable properties as per you needs.
```
    System.setProperty("interimLogCount","10");   //intermitent reporting after processing every 10 messages. 
    System.setProperty("processCapacity","50");   //processing capacity of 50, application will stop consuming any further messages.
    System.setProperty("notificationFile","src/main/resources/notifications.json");  //input file containing notification messages
```

Step 3: Application uses following external jars from lib folder

1. JSON parsing
   jackson*.jar
2. JUnit
   junit.4.10.jar
   
Step 4: Run JUnit file : TestNotificationTestCase.java

Step 5: Console output is uploaded in outputg/output.log file.
       Sample output logs.

![alt text](https://github.com/ajaysh/sales-notification-app/blob/master/image.PNG)

