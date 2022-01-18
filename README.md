<h1 align="center">Desafio back votos</h1>


### Pré-requisitos

Para rodar a api será necessário ter instalado na máquina o docker

Para rodar os testes automatizados deve ter instalado o java 8 na máquina (jdk8)

### 🎲 Buildando e Executando a API

```bash
# Clone este repositório
$ git clone https://github.com/lucassilvasg1/desafio-back-votos

# Abra o terminal na pasta raiz do projeto

# Execute o comando: 
      docker-compose up -d
      # Este comando irá gerar as imagens e criar o ambiente completo da api sem executar os testes.

# Para executar os testes automatizados:
      mvn verify
      # Irá rodar somente os testes automatizados 
      
# O swagger está disponível na url:
      http://localhost:8080/api/swagger-ui.html

```

### Sobre a implementação

O projeto foi implementado utilizando a linguagem JAVA, com o framework Spring

O banco de dados utilizado foi o postgresql

Os testes de API e integração foi implementado com o JUnit 5 com o MockMvc

Foi utilizado o plugin SonarLint na IDE Eclipse para checagem de código limpo

O Docker e Docker Compose foram configurados para a api subir através de seus containers específicos
  
        
