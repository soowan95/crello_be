#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/crello/be

cd $REPOSITORY

JAR_NAME=crello_be-0.0.1-SNAPSHOT.jar

CURRENT_PID=$(pgrep -f crello)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할 애플리케이션이 없습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> Deploy - jar"
nohup java -jar Dserver.port:8081 Dspring.profiles.active:prod $JAR_NAME > /dev/null 2>&1 &