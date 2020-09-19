#!/usr/bin/env bash

pwd
echo "CURRENT DIR : $PWD"
pwd
git pull
pwd


MODULE_DIR="camel-springboot-rest"

#setup for docker
#sysctl -w vm.max_map_count=262144

echo "Building ${MODULE_DIR} app"
#START BUILDING
#build application
mvn clean install -f ${MODULE_DIR}/pom.xml

rc=$?
if [ $rc -ne 0 ]; then
  echo 'Build failed. Exiting...'
  exit $rc
fi

echo "Building Docker image for ${MODULE_DIR} app"

mvn dockerfile:build -f ${MODULE_DIR}/pom.xml

#Checking maven result for failure...
rc=$?
if [ $rc -ne 0 ]; then
  echo 'Build failed. Exiting...'
  exit $rc
fi

#default is docker-compose-dev.yml file
DOCKER_COMPOSE_FILE=docker-compose-dev.yml


echo "Stopping docker containers..."
docker-compose -f $DOCKER_COMPOSE_FILE down
echo "Starting docker containers"
docker-compose -f $DOCKER_COMPOSE_FILE up -d

echo "COMPLETED"
exit
