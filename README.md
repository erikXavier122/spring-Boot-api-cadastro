[![Build Status](https://travis-ci.com/givanthak/spring-boot-rest-api-tutorial.svg?branch=master)](https://travis-ci.com/givanthak/spring-boot-rest-api-tutorial)
[![Known Vulnerabilities](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial/badge.svg)](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial)



# Spring-boot-api-cadastro

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/erikXavier122/spring-boot-api-cadastro.git
```

**2. Upping application and database with docker**
```bash
docker-compose up -d
```

**3. Access application with this link**
+ Swagger `http://localhost:8080/swagger-ui/#/cadastro-controler/`

## Explore Rest APIs

The app defines following CRUD APIs.

    POST /cadastro/v1/save
    
    GET /cadastro/v1/getAll

    GET /cadastro/v1/getByCpf
    
    GET /cadastro/v1/getByEmail

    GET /cadastro/v1/getByTelephone
    
    PUT /cadastro/v1/updateByCpf

    PUT /cadastro/v1/updateByEmail
    
    PUT /cadastro/v1/updateByTelephone
    
    DELETE /cadastro/v1/deleteByCpf

    DELETE /cadastro/v1/deleteByEmail

    DELETE /cadastro/v1/deleteByTelephone