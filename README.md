# aws-elasticbeanstalk-worker-spring-boot-example
Example of a Spring Boot worker application for AWS ElasticBeanstalk

# Purpose of this sample project
Give a quick end-to-end overview on how to implement a worker application for ElasticBeanstalk using Spring Boot.

# Setup

## Build
Use 
    
    mvn clean package

to build the application and create a proper .war file in

    [PROJECT_DIR]/target/

## Deploy
The easiest way to test the application is to follow the following instruction:

* Create a new ElasticBeanstalk application
* Create a new environment within your ElasticBeanstalk application
* Choose 'Worker Environment'
* On 'Preconfigured Platform' choose 'Tomcat'
* On 'Application Code' choose 'Upload your code' and select the .war file
* Click on 'Configure More Options'
* Click on 'Modify' on 'Worker' and configure the path to be: /sqs

Along with the environment (if you choose the default option in the ElasticBeanstalk configuration, window 'Worker' to auto generate a queue),
the environment will create a neq queue for you.
Additionally, a DeadLetterQueue is created to collect messages that failed to process.

ElasticBeanstalk also configures all permissions properly for you, hence you don't have to configure anything else then the points described above.

## Test
If everything created successfully, go to "Simple Queue Service" in your AWS console and select the newly generated queue.
Click in the action menu on 'Send Message' and enter a message JSON.
After sending the message, your worker environment will process the message.
You can see the logs in your ElasticBeanstalk environment on the tab 'Logs'.

## Best Practice
* Choose to *not* assign a public IP address to your worker. This increase security as the worker instance won't be accessible from the internet.
* Use AutoScaling and CloudWatch metrics to automatically scale your worker fleet if more load has to be processed.
* Consider using spot instances to reduce costs in case your messages are not time critical.
* Don't return a HTTP status 200 immediately while starting a batch-thread / background thread / whatever (async message processing). As soon as your endpoint returns HTTP status 200, the message is deleted from the queue making it impossible to be retried by another node. Hence, if your background-thread fails, you would have to re-schedule the message for your own or handle the error differently.

# Resources
* ElasticBeanstalk: https://aws.amazon.com/elasticbeanstalk/
* ElasticBeanstalk Worker Environments: http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/using-features-managing-env-tiers.html
* Spring: https://spring.io

# Feedback
Feel free to contact me on GitHub or on Twitter (@bernhard_keprt) if you have any questions.
Also feel free to give me feedback on the example code, always looking forward to improve something, first of all: myself ;)
