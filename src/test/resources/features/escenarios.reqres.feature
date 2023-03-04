Feature: Como certificador
  Yo quiero validar las apis de reqres

  Background:
    Given que el usuario cuenta con la api reqres

  @testCreate
  Scenario Outline: Crear usuario exitoso
    When el usuario envia los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    Then la respuesta de la api presenta el codigo 201
    And en la respuesta la fecha de creacion debe ser la fecha actual
    Examples:
      | name  | job       |
      | jorge | consultor |
#      | santiago | futbolista |
#      | natalia  | analista   |

  @testRead
  Scenario: Obtener listado de usuarios
    When el usuario realice la peticion para listar usuarios
    Then la respuesta de la api presenta el codigo 200
    And en la respuesta debe presentar el campo llamado total con valor 12

  @testUpdate
  Scenario Outline: actualizar usuario exitoso
    When el usuario envia los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    And el usuario luego envia los datos del usuario a actualizar
      | name   | job   |
      | <name> | <job> |
    Then la respuesta de la api presenta el codigo 200
    And en la respuesta la fecha de actualizacion debe ser la fecha actual
    Examples:
      | name     | job        |
      | santiago | futbolista |

  @testDelete
  Scenario Outline: Eliminar usuario exitoso
    When el usuario envia los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    And el usuario envia la peticion para eliminar usuario
    Then la respuesta de la api presenta el codigo 204
    Examples:
      | name   | job           |
      | carlos | desarrollador |


