# Lanchonete
<b>Aplicação Api Rest Lanchonete</b>
<body>
<b>Core</b>: Spring-Boot <br/>
<b>Banco</b>: Postgres <br/>
<b>Segurança</b>: Spring-security + JWT(JSON Web Token) <br/>
<b>Documentação</b>: Swagger 2 + <a href='https://github.com/springfox/springfox'>SpringFox</a><br/>
<b>Gerenciador de dependencia</b>: Maven <br/>
<b>Arquitetura</b>: MVC <br/>
<b>Web Container</b>: Tomcat (embedded)

<br/>
<br/>
<h1>Outras dependencias</h1>
<a href="https://projectlombok.org">Lombok</a> <br/>
Utilizado para diminuir a verbosidade nas classes da camada de modelo, foi utilizada a anotação @Data que cria os getters,
setters, equals, hashcode, toString.</br>

<h1>Instruções</h1>
<b>Primeiro passo</b>: configurar o arquivo application.properties(src/main/resources/application.properties) colocando o
ip:porta do banco, criar uma instância com o nome lanchonete, ao subir o projeto o ddl-auto está configurado como
update e isso vai fazer com que ele crie as tabelas do projeto. Configurar uma porta para subir o 
tomcat está configurado a porta 8086. <br/><br/>

<b>Segundo passo</b>: Entrar na pasta raiz do projeto e executar o comando "mvn clean install", após finalizar entrar
na pasta target e executar o comando "java -jar lanchonete-0.0.1-SNAPSHOT.jar". Após executar a documentação da api já vai
estar disponível no endereço http://host:porta/swagger-ui.html (Exemplo:http://localhost:8086/swagger-ui.html).<br/><br/>

<b>Terceiro passo</b>: Efetuar o login utilizando um utilitário para realizar requisições http 
(Acr/Postman) para efetuar o login deve ser feita uma requisição POST para a url http://host:porta/login com o seguinte objeto(JSON)
no body da requisição.<br/>

```json
{
  "usuario":"admin",
  "senha":"finch"
}
```

O retorno dessa requisição será um token conforme abaixo.

```json
Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NzkyNzI0OX0.xGAGzAy-p_33iHrnL1edZmYqmen_gGe2JLjX9_UrUFSjdbiFZ3boJxL63bpruuA9bWr7W-NnWgIR2YCQkh34kQ
```
Esse token deve ser inserido no header de toda requisição com a chave authorization, caso contrário você vai receber
um retorno 403 (acesso negado), o token tem validade de 1 dia(24h)<br/>

<b>Quarto passo:</b> Para facilitar a inclusão dos ingredientes existe um método que inclui todos conforme descrito no
documento, para executa-lo basta fazer uma requisição POST para a url http://host:porta/ingrediente/inserirTodos (documentação
do método esta descrito no Swagger).<br/>

<b>Quinto passo</b> Abaixo segue um exemplo de um json de inserção do lanche.

```json

{ 
  "nome":"X-Salada",
  "ingredientes":[
    { 
      "quantidade":1,
      "idIngrediente":1
    },
    { 
      "quantidade":2,
      "idIngrediente":5
    },
    { 
      "quantidade":4,
      "idIngrediente":3
    }
  ] 	
}

```

O método inclui o lanche e retorna o mesmo com o preço já calculado e mostrando em que promoções ele se enquadrou. (Exemplo abaixo)

```json

{
	"id": 1,
	"nome": "X-Salada 2",
	"preco": 9.2,
	"tipoPromocao": [
		"MUITO_QUEIJO",
		"LIGHT"
	],
	"ingredientes": [{
			"id": 1,
			"quantidade": 1,
			"idLanche": 1,
			"idIngrediente": 1,
			"ingrediente": {
				"id": 1,
				"nome": "Alface",
				"preco": 0.4,
				"tipoIngrediente": "ALFACE"
			}
		},
		{
			"id": 2,
			"quantidade": 2,
			"idLanche": 1,
			"idIngrediente": 5,
			"ingrediente": {
				"id": 5,
				"nome": "Hamburger",
				"preco": 3,
				"tipoIngrediente": "HAMBURGUER"
			}
		},
		{
			"id": 3,
			"quantidade": 4,
			"idLanche": 1,
			"idIngrediente": 3,
			"ingrediente": {
				"id": 3,
				"nome": "Queijo",
				"preco": 1.5,
				"tipoIngrediente": "QUEIJO"
			}
		}
	]
}


```
<h1>Considerações Finais</h1><br/>
Todos os métodos estão documentados, basta acessar a página do swagger na aplicação(http://host:porta/swagger-ui.html)
no documento não descrevia se as promoções eram acumulativas ou não, eu implementei de forma acumulativa. Em relação a 
novas features acho que seria interessante implementar um cadastro de promoções, podendo criar novas promoções e desativar
as antigas. Também acho indispensável a implementação de testes unitários, porém não obtive tempo suficiente para faze-los. 
</body>





