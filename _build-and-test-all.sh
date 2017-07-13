#! /bin/bash

set -e

echo DOCKER_HOST_IP is $DOCKER_HOST_IP

DOCKER_COMPOSE="docker-compose -p items_and_bids"

while [ "$1" = "-f" ] ; do
  shift;
  DOCKER_COMPOSE="$DOCKER_COMPOSE -f ${1?}"
  shift
done

if [ "$1" = "--use-existing" ] ; then
  shift;
else
  ${DOCKER_COMPOSE?} stop
  ${DOCKER_COMPOSE?} rm -v --force
fi

NO_RM=false

if [ "$1" = "--no-rm" ] ; then
  NO_RM=true
  shift
fi

${DOCKER_COMPOSE?} up -d mongodb $EXTRA_INFRASTRUCTURE_SERVICES

${DOCKER_COMPOSE?} build

${DOCKER_COMPOSE?} up -d

echo "Waiting for services"
echo $DOCKER_HOST_IP
./wait-for-services.sh $DOCKER_HOST_IP 8081 8082 8083 8084

set -e

#if [ $NO_RM = false ] ; then
#  ${DOCKER_COMPOSE?} stop
#  ${DOCKER_COMPOSE?} rm -v --force
#fi
