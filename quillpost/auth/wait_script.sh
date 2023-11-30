#!/bin/bash
until nc -z -v -w30 "auth-db" 3306
do
    echo "Waiting for database connection..."
    sleep 10
done
python manage.py makemigrations
python manage.py migrate
python manage.py runserver 0.0.0.0:8000
