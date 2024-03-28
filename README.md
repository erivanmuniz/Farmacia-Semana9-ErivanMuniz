 #Farmacia-Semana-10-ErivanMuniz

 #Criação da imagem da aplicação 

FROM openjdk:17-jdk
WORKDIR /app-jdk
COPY target/api-0.0.1-SNAPSHOT.jar .
ARG PORT_BUILD=8080
ENV PORT=$PORT_BUILD
EXPOSE $PORT_BUILD
CMD java -jar api-0.0.1-SNAPSHOT.jar


//Comando para executar o arquivo acima e criar a imagem da aplicação
build -t erivanmuniz/app-farma:1.0 . 

//Subindo a aplicação para o docker hub
//Fazer login no seu perfil do docker
docker login -u seu_usuario
coloque sua senha e após isso, uma mensagem de login bem sucedido aparecerá

docker tag erivanmuniz/app-farma:1.0 seu_usuario/app-farma:1.0
sua imagem vai aparecer assim: 
seu_usuario/app-farma:1.0

comando para fazer o push 
docker push seu_usuario/app-farma:1.0

sua aplicação estará no docker hub


#Subir um container para criação de um volume para persistencia dos dados do banco 

docker run --name db_farmacia -v mysql-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=256231 -e MYSQL_DATABASE=dbfarmacia -d mysql:8.0.33

#Gerenciar redes para comunicação dos serviços

//comando para criar a rede 
docker network create --driver bridge minha-bridge 

//Comando para criar um container do banco e conectar ele na minha rede
docker run -d --network minha-bridge --name db_farmacia -e MYSQL_ROOT_PASSWORD='256231' -e MYSQL_DATABASE=dbfarmacia -p 3307:3306 mysql:8.0.33
//Comando para criar a aplicação para comunicar com o container do banco
 docker run -d --network minha-bridge --name farmacia -p 8080:8080 -e MYSQL_HOST=db_farmacia erivan41/app-farma:1.0

//Comandos para colocar a aplicação e o banco na mesma rede

docker network connect minha-bridge db_farmacia
docker network connect minha-bridge farmacia_api

#Usando o docker compose para comunicar os serviços do projeto

//Estrutura do arquivo

version: '3.9'

services:
  mysql:
    image: mysql:latest
    container_name: db_farmacia
    environment:
      MYSQL_ROOT_PASSWORD: sua_senha
      MYSQL_DATABASE: dbfarmacia
    volumes:
      - volume-db-farmacia:/var/lib/mysql
    ports:
      - "3307:3306"
    networks:
      - redeapi

  farmacia_api:
    image: erivan41/app-farma:1.0
    container_name: farmacia_api
    ports:
      - "8081:8080"
    environment:
      MYSQL_HOST: db_farmacia
      MYSQL_DATABASE: dbfarmacia
    networks:
      - redeapi
    depends_on:
      - mysql 

networks:
  redeapi:
    driver: bridge

volumes:
  volume-db-farmacia:

//Comando comunicação dos serviços

Dentro da pasta onde está o arquivo coloque o seguinte comando:

docker-compose up


# Farmacia-Semana9-ErivanMuniz
 Projeto API REST - Semana 9 


