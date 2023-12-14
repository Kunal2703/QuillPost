
# QuillPost: A Microservice Architecture based Blog Posting App

## Overview

QuillPost is a microservices architecture for a blog posting application which consists of the following services-

    1. Authentication Service (Django): Handles user authentication and authorization
    2. Commensts Service (Django): Manages user comments on blog posts.
    3. Post Service (Spring Boot): Responsible for blog post-related functionalities.
    4. Frontend Service (React.js): Provides the user interfce for the app

The application can be run using docker-compose or Kubernetes. The docker-compose.yml and kubernetes manifests files are also included.


## Run the project using docker-compose

```bash
  cd quillpost
  docker-compose up -d
```
> The app can then be accessed on localhost:3000
## Run the project using kubernetes manifests

```bash
  cd quillpost
  cd kubernetes
  kubectl apply -f combinedmanifests.yaml
```
