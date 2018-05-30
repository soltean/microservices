#! /bin/bash

set -e


. ./set-env.sh

docker-compose -f docker-compose-mysql.yml down -v

docker-compose -f docker-compose-mysql.yml up -d --build zookeeper mysql kafka

./wait-for-mysql.sh

docker-compose -f docker-compose-mysql.yml up -d --build cdcservice

./wait-for-services.sh $DOCKER_HOST_IP "8099"

docker-compose -f docker-compose-mysql.yml up -d --build

./wait-for-services.sh $DOCKER_HOST_IP "8081 8082"


docker-compose -f docker-compose-mysql.yml down -v
