# language: pt
# coding: utf-8
# encoding: utf-8

#noinspection NonAsciiCharacters
Funcionalidade: Relatórios gerenciais

  Como usuário do sistema,
  Preciso emitir alguns relatórios,
  Para facilitar o gerenciamento de recursos durante a guerra.

  Cenário: Obter o percentual de traidores
    Dado que desejo saber o percentual de traidores
    Quando executar a chamada ao endpoint "/report/traitors/percentage"
    Então a chamada deverá retornar o status "200"
    E o percentual de traidores deve ser de "34.0"%

  Cenário: Obter o percentual de soldados
    Dado que desejo saber o percentual de soldados
    Quando executar a chamada ao endpoint "/report/soldiers/percentage"
    Então a chamada deverá retornar o status "200"
    E o percentual de traidores deve ser de "66.0"%

  Cenário: Obter o total de pontos perdidos a traidores
    Dado que desejo saber o total de pontos perdidos a traidores
    Quando executar a chamada ao endpoint "/report/traitors/lostpoints"
    Então a chamada deverá retornar o status "200"
    E o total de pontos perdidos a traidores deve ser de "1000"

  Cenário: Obter a média de cada recurso por soldado
    Dado que desejo saber a média de recursos por soldados não traidores
    Quando executar a chamada ao endpoint "/report/resources/average"
    Então a chamada deverá retornar o status "200"
    E a média de todos os itens por soldado deve ser de "100.0"