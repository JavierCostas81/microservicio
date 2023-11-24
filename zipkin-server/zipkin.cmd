@echo off
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
set MYSQL_USER=zipkin
set MYSQL_PASS=zipkin
java -jar ./zipkin-server-2.24.3-exec.jar



docker run -p 9411:9411 --name zipkin-server --network springcloud -e RABBIT_ADDRESS=microservicios-rabbitmq38:5672 -e STORAGE_TYPE=mysql -e MYSQL_USER=zipkin -e MYSQL_PASS=zipkin -e MYSQL_HOST=microservicios-mysql8:3306 openzipkin/zipkin:2.1.0

docker pull 