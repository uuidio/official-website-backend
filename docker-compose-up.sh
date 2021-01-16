#!/usr/bin/env bash

IMAGE_CONFIG="ssc-website.yml"
CONTAINER_ADDRESS="172.18.12.62:2375"

docker-compose -f $IMAGE_CONFIG -H $CONTAINER_ADDRESS stop
docker-compose -f $IMAGE_CONFIG -H $CONTAINER_ADDRESS rm -f
docker-compose -f $IMAGE_CONFIG -H $CONTAINER_ADDRESS pull
docker-compose -f $IMAGE_CONFIG -H $CONTAINER_ADDRESS up -d