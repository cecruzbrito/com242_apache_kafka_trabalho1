<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/darlosss/com242_apache_kafka_trabalho1?color=a015f5">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/darlosss/com242_apache_kafka_trabalho1">

  <a href="https://github.com/darlosss/com242_apache_kafka_trabalho1/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/darlosss/com242_apache_kafka_trabalho1">
  </a>

<img alt="License" src="https://img.shields.io/crates/l/ap?color=red">
  <a href="https://github.com/darlosss/com242_apache_kafka_trabalho1/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/darlosss/com242_apache_kafka_trabalho1?style=social">
  </a>
</p>

<p align="center">
  <a href="https://github.com/darlosss/com242_apache_kafka_trabalho1">
    <img src="https://upload.wikimedia.org/wikipedia/commons/archive/5/53/20210416084742%21Apache_kafka_wordtype.svg" height="285" width="485" alt="RepiMe-logo" />
  </a>
</p>

<p align="center">
    <a href="https://www.scala-lang.org/">
        <img align="center" alt="RepiMe-Flutter" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/scala/scala-original-wordmark.svg">
    </a>
    <a href="https://www.java.com/pt-BR/">
        <img align="center" alt="RepiMe-Dart" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain-wordmark.svg">
    </a>

</p>

# Apache Kafka - Serviços de Mensageria

O Apache Kafka é uma plataforma de streaming distribuída assíncrona que permite o processamento de dados em tempo real.

---
## Sobre

Para desenvolver um projeto básico, na disciplina COM241 - Sistemas Distribuídos da Universidade Federal de Itajubá, utilizando o Apache Kafka para enviar e receber temperaturas convertidas em dois tópicos diferentes (temperatura atual e temperatura convertida), é necessário configurar um produtor de dados que envie as temperaturas para um tópico específico e um consumidor que receba os dados e realize as conversões necessárias antes de enviar para o tópico de temperatura convertida. Além disso, é preciso definir a estrutura da mensagem a ser enviada, como formato, codificação e dados adicionais, e configurar a comunicação entre os componentes através de um servidor Kafka, que permitirá o envio e recebimento das mensagens de forma assíncrona. Com isso, é possível utilizar o Apache Kafka para criar um sistema robusto e escalável de processamento de dados em tempo real para temperatura.

As orientações estão divididas nos seguintes tópicos:

- [Apache Kafka - Serviços de Mensageria](#apache-kafka---serviços-de-mensageria)
  - [Sobre](#sobre)
  - [Funcionalidades :gear:](#funcionalidades-gear)
  - [Pré-requisitos e configuração :hammer\_and\_wrench:](#pré-requisitos-e-configuração-hammer_and_wrench)
  - [Tecnologias :technologist:](#tecnologias-technologist)
  - [Contribuidores](#contribuidores)

---
## Funcionalidades :gear:

 - [x] Comunicação da aplicação com o broker
 - [x] Envio de mensagens para os tópicos criados no broker - Publish
 - [x] Consumo de mensagens enviadas para os tópicos - Subscribe
 - [x] Propriedades para identificação dos grupIds de um mesmo tópico - Groups
 - [x] Envio de temperaturas randômicas
 - [x] Serviço destinado para conversão da temperatura fornecido para Kelvin
 - [x] Serviço destinado para conversão da temperatura fornecido para Fahrenheit 

---
## Pré-requisitos e configuração :hammer_and_wrench:
No geral, para executar a aplicação é recomendado que o sistema já possua:

    > Java JDK 11+;
    > Binários do Apache Kafka;
    > Intellij IDEA Comuntity ou Ultimate;
    > Maven.

Os pormenores de configuração do Kafka/Zookeeper estão presentes na [documentação](/docs) dessa aplicação protótipo.

Para executar a aplicação é necessário:

```bash

# Clone este repositório com
$ git clone https://github.com/darlosss/com242_apache_kafka_trabalho1

# Acesse a pasta dos binários do kafka e inicie os servidores pelo terminal
# Zookeeper server
$ zookeeper-server-start.sh config/zookeeper.properties

# Kafka broker
$ kafka-server-start.sh config/server.properties

```

---
## Tecnologias :technologist:
    O ponto de início deste projeto foi uma aplicação Java, as dependências utilizadas estão presentes no pom.xml 
---
Aplicação:

    -> Java
    - org.projectlombok: lombok ^1.18.26
    - org.apache.kafka: kafka-clients ^2.6.3
    - org.slf4j: slf4j-simple ^1.7.29
    - com.google.code.gson: gson ^2.10.1
---
Utilitários:

    -> Dev
    - Intellij IDEA Ultimate
    - Binary Kafka
---  

## Contribuidores

<table>
  <tr>
    <td align="center"><a href="https://github.com/darlosss"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/72506461?v=4" width="100px;" alt=""/><br /><sub><b>Carlos Eduardo</b></sub></a><br /><a href="https://github.com/darlosss/repime" title="RepiMe">:technologist:</a></td>
    <td align="center"><a href="https://github.com/MatMB115"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/63670910?v=4" width="100px;" alt=""/><br /><sub><b>Matheus Martins</b></sub></a><br /><a href="https://github.com/MatMB115/repime" title="RepiMe">:technologist:</a></td>
    <td align="center"><a href="https://github.com/adriano-12"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/66391807?v=4" width="100px;" alt=""/><br /><sub><b>Adriano Lucas</b></sub></a><br /><a href="https://github.com/adriano-12" title="RepiMe">:technologist:</a></td>
  </tr>
</table>
