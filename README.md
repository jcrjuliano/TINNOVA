# TINNOVA Test
<br />
Este documento contém todas as funcionálidades da API e como utilizar;
<br />
## Getting Started
<br />
A API foi toda desenvolvida em Spring utilizando um banco de dados in-memory para testes locais.<br />

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

## End-points (Rotas):

Todos end-points abaixo são considerados a partir do End-point:<br />
URL: http://localhost/veiculos

### **End-point de cadastro**

###### Cadastro novo veiculo:

**Verbo HTTP:** POST<br />
**Header requerido:** <br />
	Key: Content-Type<br />
	Value: application/json<br />
**Body:**<br />
Exemplo de JSON para cadastro:<br />
{<br />
    "veiculo" : "Fiesta",<br />
    "marca" : "Ford",<br />
    "ano":2020,<br />
    "descricao": "Branco, completo",<br />
    "vendido" : false<br />
}<br />

**OBS:** Para o campo "marca" são aceito apenas os valores: <br />
Ford, Fiat, Chevrolet ou Volkswagen.<br />


### **End-points de consulta**

###### Listar todos:

**Verbo HTTP:** GET<br />
**Opcional:** Query Params: apenasDisponiveis=true <br />
**Resultado esperado:** Lista todos veículos cadastrados<br />
Caso seja inserido o valor opcional com True, trará apenas os veículos disponíveis para venda (status vendido = false).<br />
<br />
###### Listar pelo ID:
**Verbo HTTP:** GET<br />
**URL:** /{veiculoId}<br />
**Resultado esperado:** Retorna apenas o veículo com o ID correpondente ao informado na URL.<br />

###### Lista por década:
**Verbo HTTP:** GET<br />
**URL:** /por-decada<br />
**Opcional:** Query Params: apenasDisponiveis=true<br />
**Resultado esperado:** Traz por época a quantidade de veículos cadastrada no banco de dados, caso o parametro opcional seja setado para TRUE, trará apenas os as quantidades dos veículos disponíveis para venda.<br />

###### Lista por fabricante:
**Verbo HTTP:** GET<br />
**URL:** /por-fabricante<br />
**Opcional:** Query Params: apenasDisponiveis=true<br />
**Resultado esperado:** Traz por fabricante a quantidade de veículos cadastrada no banco de dados, caso o parametro opcional seja setado para TRUE, trará apenas os as quantidades dos veículos disponíveis para venda.<br />

###### Lista por fabricante:
**Verbo HTTP:** GET<br />
**URL:** /ultima-semana<br />
**Resultado esperado:** Traz os veículos cadastrados nos últimos 7 dias.<br />

###### Lista por fabricante:

**Verbo HTTP:** GET<br />
**URL:** /disponiveis<br />
**Resultado esperado:** Retorna um número Inteiro correspondendo ao número de veículos disponíveis para venda no banco de dados.<br />

### **End-points de Atualização**

###### Atualizar por ID:

**Verbo HTTP:** PUT<br />
**URL:** /{veiculoId} <br />
**Header requerido:** <br />
	Key: Content-Type<br />
	Value: application/json<br />
**Body:** Novos dados do veículo<br />
Exemplo:<br />
{<br />
    "veiculo" : "Focus",<br />
    "marca" : "Ford",<br />
    "ano": 2012,<br />
    "descricao": "Preto, completo",<br />
    "vendido" : true<br />
}<br />

**Resultado esperado:** Atualiza no banco de dados o registro com o ID igual ao da URL com os novos dados passados no body, retorna Http Status 200 - OK, atualizado com sucesso. <br />

###### Atualização parcial do recurso:

**Verbo HTTP:** PATCH<br />
**URL:** /{veiculoId} <br />
**Header requerido:** <br />
	Key: Content-Type<br />
	Value: application/json<br />
**Body:** Apenas os valores que você quer atualizar, no exemplo, vamos alterar o status de vendido e o Ano:<br />
{<br />
    "ano": 2020,<br />
    "vendido" : false<br />
}

**Resultado esperado:** Atualiza no banco de dados o registro com o ID igual ao da URL porém, apenas os valores passados no objeto no body, retorna Http Status 200 - OK, atualizado com sucesso. <br />

### **End-point de Exclusão**
**Verbo HTTP:** DELETE<br />
**URL:** /{veiculoId}<br />
**Resultado esperado:** Registro excluído do banco de dados, Http Status 204 - No content.<br />

## Author

**Juliano Rosa** - [jcrjuliano](https://github.com/jcrjuliano)
