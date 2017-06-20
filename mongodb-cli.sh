#! /bin/bash

docker run --link mongodb:mongodb -i -t mongo:3.0.4 /usr/bin/mongo --host mongodb
