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

## Comandos
 ** Para gerar o pacote WAR, o seguinte comando deve ser executado no diretório raiz do projeto (o qual contém o arquivo "pom.xml"):
mvn package

Feito isto, o arquivo do pacote será: target/clinica.war.

Para instalar o sistema no Tomcat, será necessário copiar o WAR para o diretório apropriado e possivelmente reiniciar o serviço:
sudo cp target/clinica.war /var/lib/tomcat9/webapps
sudo service tomcat9 restart

## Deploy 
* Comando: mvn deploy
