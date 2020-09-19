    Run the below command from the root directory
    ./buildAndDeploy.sh
    
    which will:
    
    * build the project via maven running all unit & integration tests
    * create the docker image for the app if previous step is successful
    * start the service using docker-compose (2 containers: camel service + mongodb) 
    
    
    camel-springboot-rest/target/cucumber/books.html contains the cucumber integration test results