# language: pt
# coding: utf-8
# encoding: utf-8

#noinspection NonAsciiCharacters
Funcionalidade: Cadastro de soldados rebeldes

  Como usuário do sistema,
  Preciso cadastrar um soldado rebelde jutamente com seu inventário,
  Para facilitar a localização e negociação de recursos durante a guerra.

  Delineação do Cenário: <descricaoCenario> - <resultadoEsperado>
    Dado que desejo cadastrar um soldado rebelde com as seguintes informações:
      | <name> | <age> | <gender> | <latitude> | <longitude> | <baseName> | <water> | <food> | <weapon> | <ammo> |
    Quando executar a ação POST para o endpoint "/soldier"
    Então a criação deverá retornar o status "<resultadoEsperado>"

    Exemplos:
      | descricaoCenario                                | resultadoEsperado | name   | age | gender | latitude | longitude | baseName   | water | food | weapon | ammo |
      | Cadastrar soldado com sucesso                   | 201               | Filipe | 28  | M      | 12345    | 12345     | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem nome                      | 400               |        | 28  | M      | 12345    | 12345     | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem idade                     | 400               | Filipe |     | M      | 12345    | 12345     | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem genero                    | 400               | Filipe | 28  |        | 12345    | 12345     | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem latitude                  | 400               | Filipe | 28  | M      |          | 12345     | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem longitude                 | 400               | Filipe | 28  | M      | 12345    |           | Groelândia | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem nome da base              | 400               | Filipe | 28  | M      | 12345    | 12345     |            | 5     | 10   | 15     | 20   |
      | Erro ao cadastrar sem informar total de água    | 400               | Filipe | 28  | M      | 12345    | 12345     |            |       | 10   | 15     | 20   |
      | Erro ao cadastrar sem informar total de comida  | 400               | Filipe | 28  | M      | 12345    | 12345     |            |       | 10   | 15     | 20   |
      | Erro ao cadastrar sem informar total de armas   | 400               | Filipe | 28  | M      | 12345    | 12345     |            |       | 10   | 15     | 20   |
      | Erro ao cadastrar sem informar total de munição | 400               | Filipe | 28  | M      | 12345    | 12345     |            |       | 10   | 15     | 20   |