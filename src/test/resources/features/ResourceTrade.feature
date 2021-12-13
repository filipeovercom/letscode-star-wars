# language: pt
# coding: utf-8
# encoding: utf-8

#noinspection NonAsciiCharacters
Funcionalidade: Negociação de recursos entre soldados rebeldes

  Como usuário do sistema,
  Preciso negociar recursos com outro soldado rebelde,
  Para facilitar o gerenciamento de recursos durante a guerra.

  Delineação do Cenário: <descricaoCenario> - <resultadoEsperado>
    Dado que desejo realizar uma negociação de recursos com as seguintes informações:
      | <idSoldierA> | <idSoldierB> | <waterA> | <foodA> | <weaponA> | <ammoA> | <waterB> | <foodB> | <weaponB> | <ammoB> |
    Quando executar a negociação através do endpoint "/trade"
    Então a negociação deverá retornar o status "<resultadoEsperado>"

    Exemplos:
      | descricaoCenario                           | resultadoEsperado | idSoldierA | idSoldierB | waterA | foodA | weaponA | ammoA | waterB | foodB | weaponB | ammoB |
      | Erro ao negociar o que não possui - water  | 400               | 1          | 2          | 120    | 1     | 1       | 1     | 120    | 1     | 1       | 1     |
      | Erro ao negociar o que não possui - food   | 400               | 1          | 2          | 1      | 120   | 1       | 1     | 1      | 120   | 1       | 1     |
      | Erro ao negociar o que não possui - weapon | 400               | 1          | 2          | 1      | 1     | 120     | 1     | 1      | 1     | 120     | 1     |
      | Erro ao negociar o que não possui - ammo   | 400               | 1          | 2          | 1      | 1     | 1       | 120   | 1      | 1     | 1       | 120   |
      | Erro ao negociar com valor negativo        | 400               | 1          | 2          | -120   | 0     | 1       | 0     | 0      | 0     | 0       | 2     |
      | Erro ao negociar com traidor               | 400               | 1          | 3          | 1      | 1     | 1       | 1     | 1      | 1     | 1       | 1     |
      | Erro ao negociar com pontuacao diferente   | 400               | 1          | 3          | 2      | 1     | 1       | 1     | 1      | 1     | 1       | 1     |
      | Sucesso ao negociar tudo que tem           | 200               | 1          | 2          | 100    | 100   | 100     | 100   | 100    | 100   | 100     | 100   |
      | Sucesso ao negociar quantidades zeradas    | 200               | 1          | 2          | 0      | 0     | 0       | 0     | 0      | 0     | 0       | 0     |
      | Sucesso ao negociar mesmas quantidades     | 200               | 1          | 2          | 4      | 3     | 2       | 1     | 4      | 3     | 2       | 1     |
      | Sucesso ao negociar - examplo prova        | 200               | 1          | 2          | 1      | 0     | 1       | 0     | 0      | 0     | 0       | 2     |