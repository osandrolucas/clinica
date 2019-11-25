## clinica

Diretório criado para armazenar projeto da cadeira de Validação e Verificação de Sistemas do 6° semestre, cursada no IFRS.

O objetivo é automaticar o processo de validação do código fonte e deploy.

## Colaboradores
* github.com/osandrolucas
* github.com/M374LX

## Tecnologias
* Java 1.8
* MySQL
* Maven
* Tomcat 9

## Ambiente
* Lubuntu 19.04 (Máquina Virtual)
* Apache Server

## Premissas
Temos como premissa a criação de um banco de dados (MySQL) para que seja possível a realização dos testes antes da execução do deploy.

Para acessar o mysql:
mysql -u root -p
pass: root

Para acessar o database:
use nome_database;

Para listar os bancos existentes:
show databases;

Para listar as tabelas existentes:
show tables;

Para criar o banco de dados, são necessários os seguintes comandos:
mysql -u root -p
(senha)
CREATE DATABASE clinica;
CREATE DATABASE clinicatest;
QUIT

mysql -u root -p clinica < clinica.sql
(senha)

mysql -u root -p clinicatest < clinica.sql
(senha)


## Comandos para Deploy
Os comandos a seguir devem ser executados no diretório raiz do projeto (o qual contém o arquivo "pom.xml").

Para gerar o relatório do PMD:
* mvn pmd:pmd

Feito isto, o relatório será disponibilizado em: target/pmd.xml.

Para gerar o pacote WAR:
* mvn package

Feito isto, o arquivo do pacote será disponibilizado em: target/clinica.war.

Para executar o projeto, será necessário copiar o .clinica.war, citado acima, para o diretório apropriado e possivelmente reiniciar o serviço do Tomcat:
* sudo cp target/clinica.war /var/lib/tomcat9/webapps
* sudo service tomcat9 restart

Após isto, acesse o sistema via navegador através da URL http://localhost:8080/clinica/

# Apache Commands

Check the status of apache web server: 
* sudo service apache2 status

Stop, start, and restart apache web server:
* sudo service apache2 stop
* sudo service apache2 start
* sudo service apache2 restart


