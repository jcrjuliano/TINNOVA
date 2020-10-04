# TINNOVA Test

Este documento contém todas as funcionálidades da API e como utilizar;

## Getting Started

A API foi toda desenvolvida em Spring utilizando um banco de dados in-memory para testes locais.
```
É possível acessar a interface do banco de dados, para isso, basta executar a API e no navegador entrar no seguinte endereço:

http://localhost:8080/h2-console/

Saved Settings: Generic H2 (Embedded)
Setting Name: Generic H2 (Embedded)

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Sem password.
```

##End-points (Rotas):

Todos end-points abaixo são considerados a partir do End-point:
URL: http://localhost/veiculos

###**End-point de cadastro**

**Verbo HTTP:** POST
**Header requerido:** 
	Key: Content-Type
	Value: application/json
**Body:**
Exemplo de JSON para cadastro:
{
    "veiculo" : "Fiesta",
    "marca" : "Ford",
    "ano":2020,
    "descricao": "Branco, completo",
    "vendido" : false
}

**OBS:** Para o campo "marca" são aceito apenas os valores: 
Ford, Fiat, Chevrolet ou Volkswagen.


###**End-points de consulta**

###### Listar todos:

**Verbo HTTP:** GET
**Opcional:** Query Params: apenasDisponiveis=true 
**Resultado esperado:** Lista todos veículos cadastrados
Caso seja inserido o valor opcional com True, trará apenas os veículos disponíveis para venda (status vendido = false).

###### Listar pelo ID:
**Verbo HTTP:** GET
**URL:** /{veiculoId}
**Resultado esperado:** Retorna apenas o veículo com o ID correpondente ao informado na URL.

###### Lista por década:
**Verbo HTTP:** GET
**URL:** /por-decada
**Opcional:** Query Params: apenasDisponiveis=true
**Resultado esperado:** Traz por época a quantidade de veículos cadastrada no banco de dados, caso o parametro opcional seja setado para TRUE, trará apenas os as quantidades dos veículos disponíveis para venda.

###### Lista por fabricante:
**Verbo HTTP:** GET
**URL:** /por-fabricante
**Opcional:** Query Params: apenasDisponiveis=true
**Resultado esperado:** Traz por fabricante a quantidade de veículos cadastrada no banco de dados, caso o parametro opcional seja setado para TRUE, trará apenas os as quantidades dos veículos disponíveis para venda.

###### Lista por fabricante:
**Verbo HTTP:** GET
**URL:** /ultima-semana
**Resultado esperado:** Traz os veículos cadastrados nos últimos 7 dias.

###### Lista por fabricante:

**Verbo HTTP:** GET
**URL:** /disponiveis
**Resultado esperado:** Retorna um número Inteiro correspondendo ao número de veículos disponíveis para venda no banco de dados.

###**End-points de Atualização**

###### Atualizar por ID:

**Verbo HTTP:** PUT
**URL:** /{veiculoId} 
**Header requerido:** 
	Key: Content-Type
	Value: application/json
**Body:** Novos dados do veículo
Exemplo:
{
    "veiculo" : "Focus",
    "marca" : "Ford",
    "ano": 2012,
    "descricao": "Preto, completo",
    "vendido" : true
}

**Resultado esperado:** Atualiza no banco de dados o registro com o ID igual ao da URL com os novos dados passados no body, retorna Http Status 200 - OK, atualizado com sucesso. 

###### Atualização parcial do recurso:

**Verbo HTTP:** PATCH
**URL:** /{veiculoId} 
**Header requerido:** 
	Key: Content-Type
	Value: application/json
**Body:** Apenas os valores que você quer atualizar, no exemplo, vamos alterar o status de vendido e o Ano:
{
    "ano": 2020,
    "vendido" : false
}

**Resultado esperado:** Atualiza no banco de dados o registro com o ID igual ao da URL porém, apenas os valores passados no objeto no body, retorna Http Status 200 - OK, atualizado com sucesso. 

###**End-point de Exclusão**
**Verbo HTTP:** DELETE
**URL:** /{veiculoId}
**Resultado esperado:** Registro excluído do banco de dados, Http Status 204 - No content.

## Author

* **Juliano Rosa** - [jcrjuliano](https://github.com/jcrjuliano)
