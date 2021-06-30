# station
1- install docker

2- docker-compose build

3- docker-compose up

TO CHECK APIS:
4-  http://localhost:8082/lib-service/swagger-ui.html
5-  access database :http://localhost:8081/ (username: root - password : root)

6- to rebuild docker :
    a- docker-compose down -v
    b- docker-compose up --force-recreate

7-open bash : docker exec -it <containerId> /bin/bash