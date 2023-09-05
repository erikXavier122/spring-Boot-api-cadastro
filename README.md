# Spring-boot-api-cadastro

<p align="centes">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" />
<img src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white" />
<img src="https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white" />
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
</p>

> Status da Aplicação: Concluido || Em Andamento || Depreciado || Em Produção
<br><br>

## Tópicos
<p align="center">
    <a href="#sobre-o-projeto">Sobre o projeto</a> •
    <a href="#funcionalidades">Funcionalidades</a> •
    <a href="#build-e-release-e-monitorament">Build, Release e Monitoramento</a> •
    <a href="#tecnologias">Tecnologias</a> •
    <a href="#arquitetura">Arquitetura</a> •
    <a href="#pré-requisitos">Pré-requisitos</a> •
    <a href="#como-executar-o-projeto">Como executar o projeto</a> •
    <a href="#casos-de-uso">Casos de Uso</a> •
    <a href="#como-contribuir">Como contribuir</a> 
</p>

## Sobre o projeto

Este projeto, faz um CRUD dos usuarios, de usuarios cadastrados.
Puxa todos usuarios cadastrados, puxa usuarios pelo email, telephone e cpf.
Este projeto atualiza cadastro por email, telephone e cpf.
Outra funcionalidade é que ele deleta cadastros pelo email, telephone e pelo cpf.

Swagger `http://localhost:8080/swagger-ui/#/cadastro-controler/

## Funcionalidades

Funcionalidades que esse projeto realiza:

- Módulo:
  -[x] POST 

- Módulo:
  - [x] Get All
  - [x] Get By Email
  - [x] Get By Telephone
  - [x] Get By Cpf

- Módulo:
    - [x] Update By Email
    - [x] Update By Telephone
    - [x] Update By Cpf

- Módulo:
  - [x] Delete By Email
  - [x] Delete By Cpf

## Build e release e monitoramento
> Aqui você coloca o link da suas pipelines

## Tecnologias

Usei o Spring Boot
Nesse projeto usei o banco de dados MySql.

## Arquitetura

<img src="https://cdn-ajfbi.nitrocdn.com/GuYcnotRkcKfJXshTEEKnCZTOtUwxDnm/assets/images/optimized/rev-482e151/www.astera.com/wp-content/uploads/2020/01/rest-600x453-551x.png" />

## Pré-requisitos

As tecnologias necessasrias para executar a aplicação. Framework's, linguagem de progração, container e etc...

 [Java 8](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)

 [Docker](https//www.nuget.org)



## Como executar o projeto

Descreva aqui qual o processo para executar a aplicação 

No terminal, clone o projeto:

```Bash
git clone https://github.com/erikXavier122/spring-boot-api-cadastro.git

cd spring-boot-api-cadastro

docker-compose up -d mysql-db
docker-compose up api-cadastro-usuario
```

## utilidades para a api:

## Para o site web (aba de login de usuário):
A API pode ser usada para criar a funcionalidade de login de usuários em um site.
Para os donos da empresa:

## Os proprietários da empresa podem usar a API para:
Visualizar todos os usuários cadastrados.
Salvar novos cadastros de usuários.
Recuperar informações dos registros de usuários existentes.
Atualizar dados de usuários.
Excluir registros de usuários.
Características adicionais:

Tudo isso é realizado de forma organizada,
tornando a experiência de uso fácil de compreender para quem estiver utilizando a API.

Endpoints da api.

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

## Como contribuir

Pull Requests:faça um Pull Request com modificações que voce acha que seriam boas nessa api,
irei revisar e posso aplicar e meu projeto e por em pratica nos proximos projetos.

Política de Contribuição:Para voce que fez um pull request com novas funcionalidades e modificações para esta api,
por favor comente aqui no Readme.md para que eu confira as devidas modificações feitas. 