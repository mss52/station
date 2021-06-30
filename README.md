# station
1- install docker

2- run command: docker-compose build

3- run command: docker-compose up

TO CHECK APIS:
4-  http://localhost:8082/lib-service/swagger-ui.html

5-  access database :http://localhost:8081/ (username: root - password : root)

6- to rebuild docker :

    a- run command: docker-compose down -v
    
    b- run command: docker-compose up --force-recreate

7- to open bash run command: docker exec -it <containerId> /bin/bash
