Running the buildAndDeploy.sh will


    Command to run from the root directory
    ./buildAndDeploy.sh
    
    
    * builds the project via maven running all unit & integration tests
    * creates the docker image for the app if previous step is successful
    * start the service using docker-compose (2 containers: camel service + mongodb) 
    
    
    
    camel-springboot-rest/target/cucumber/books.html contains the cucumber integration test results