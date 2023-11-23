#!/bin/bash
until nc -z -v -w30 "post_db" 3306
do
    echo "Waiting for database connection..."
    sleep 10
done
java -jar app.jar
