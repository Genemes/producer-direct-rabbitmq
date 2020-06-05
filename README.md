# producer-direct-rabbitmq

Este projeto contém é um produtor e um consumidor de mensagens para o Direct Exchange desenvolvido com o Spring Boot.

O projeto implementa criação de filas (queues), exchanges e bindings via cdigo da aplicação. 
Para criar um container RabbitMQ, você pode criar um arquivo e renomeá-lo-pra
# docker-compose.yml
O conteúdo do arquivo pode ser:

version: '3.7'

services: 
    rabbitmq3-direct:
        image: rabbitmq:3-management
        ports: 
            - 5672:5672
            - 15672:15672

O arquivo deve ser executado com o comando:
# docker-compose up

O projeto consiste em uma fila de boleto que serão processadas. Para enviar uma requisição utilize o postman para enviar requisições POST para http://localhost:8081/direct


    entrada: 
    {
        "description":"Boleto energia elétrica", 
        "payValue": "250.00", 
        "dueDate": "2022-31-12"     
    }
    
    
    entrada: 
    {
        "description":"Boleto água", 
        "payValue": "75.00", 
        "dueDate": "2020-01-01"     
    }
    
    
    entrada: 
    {
        "description":"teste", 
        "payValue": "250.00", 
        "dueDate": "2020-31-12"     
    }
    

Agora execute o projeto consumer. As requisições lidas pelo consumer serão impressas no console da sua IDE.
As mensagens que apresentarem algum erro serão redirecionadas para a fila dead-letter e serão reprocessadas até três vezes. Caso a data de vencimento seja uma data passada a mensagem será redirtecionada para a fil data-exception, se o erro for outro será enviado para a fila parking-lot.
