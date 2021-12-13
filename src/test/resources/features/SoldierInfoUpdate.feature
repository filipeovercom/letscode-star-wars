# language: pt
# coding: utf-8
# encoding: utf-8

#noinspection NonAsciiCharacters
Funcionalidade: Atualização de informações dos soldados rebeldes

  Como usuário do sistema,
  Preciso atualizar os dados de um soldado rebelde,
  Para facilitar a localização e negociação de recursos durante a guerra.

  Delineação do Cenário: Localização - <descricaoCenario> - <resultadoEsperado>
    Dado que desejo atualizar a localização de um soldado rebelde com as seguintes informações:
      | <latitude> | <longitude> | <nomeDaBase> |
    Quando executar a atualização da localização no endpoint "/soldier/<id>/localization"
    Então a atualização deverá retornar o status "<resultadoEsperado>"

    Exemplos:
      | descricaoCenario                     | resultadoEsperado | id | latitude | longitude | nomeDaBase |
      | Sucesso ao atualizar                 | 200               | 1  | 12345    | 12345     | USA 1      |
      | Erro ao atualizar sem latitude       | 400               | 1  |          | 12345     | USA 2      |
      | Erro ao atualizar sem longitude      | 400               | 1  | 12345    |           | USA 3      |
      | Erro ao atualizar sem nome da base   | 400               | 1  | 12345    | 12345     |            |
      | Erro ao atualizar com id inexistente | 404               | 25 | 12345    | 12345     | USA 4      |
      | Erro ao atualizar com id errado      | 400               | a  | 12345    | 12345     | USA 4      |

  Delineação do Cenário: Traidor - <descricaoCenario> - <resultadoEsperado>
    Dado que desejo reportar um soldado como traidor
    Quando reportar a traição no endpoint "/soldier/<id>/traitor"
    Então a atualização deverá retornar o status "<resultadoEsperado>"

    Exemplos:
      | descricaoCenario                                    | resultadoEsperado | id |
      | Sucesso ao reportar                                 | 200_FALSE         | 1  |
      | Sucesso ao reportar                                 | 200_FALSE         | 1  |
      | Sucesso ao reportar - verificar marcação de traidor | 200_TRUE          | 1  |
      | Erro ao atualizar com id inexistente                | 404               | 25 |