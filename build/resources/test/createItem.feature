Feature: Project

  @Projectos
  Scenario: As admin user
  I want to create, update and delete an item
  So that i am able to manipulate the item

    Given yo tengo acceso a Todo.ly
    When yo envio una peticion POST al siguiente url https://todo.ly/api/items.json con json
    """
    {
    "Content": "New Item"
    }
    """
    Then yo espero que el codigo de respuesta sea: 200
    And yo espero que el nombre del project sea: "New Item"