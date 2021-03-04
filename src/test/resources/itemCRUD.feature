Feature: Item

  @Item
  Scenario: As admin user
  I want to create, update and delete an item
  So that i am able to manipulate the item

    Given tengo acceso a Todo.ly
    When envio una peticion POST al url http://todo.ly/api/items.json con json
    """
    {
        "Content": "Item Nuevo"
    }
    """
    Then quiero que el codigo de respuesta sea 200

    And yo espero el response body sea
    """
    {
      "Id": "IGNORE",
      "Content": "Item Nuevo",
      "ItemType": 1,
      "Checked": false,
      "ProjectId": null,
      "ParentId": null,
      "Path": "",
      "Collapsed": false,
      "DateString": null,
      "DateStringPriority": 0,
      "DueDate": "",
      "Recurrence": null,
      "ItemOrder": null,
      "Priority": 4,
      "LastSyncedDateTime": "IGNORE",
      "Children": [
      ],
      "DueDateTime": null,
      "CreatedDate": "IGNORE",
      "LastCheckedDate": null,
      "LastUpdatedDate": "IGNORE",
      "Deleted": false,
      "Notes": "",
      "InHistory": false,
      "SyncClientCreationId": null,
      "DueTimeSpecified": true,
      "OwnerId": "IGNORE"
    }
    """
    And yo obtengo el Id y lo guardo en Id_Item
    When yo envio una peticion PUT al url http://todo.ly/api/items/Id_Item.json con json
    """
    {
        "Content": "Item Nuevo"
    }
    """
    Then quiero que el codigo de respuesta sea 200
    And yo espero el response body sea
    """
    {
      "Id": Id_Item,
      "Content": "Item Nuevo",
      "ItemType": 1,
      "Checked": false,
      "ProjectId": null,
      "ParentId": null,
      "Path": "",
      "Collapsed": false,
      "DateString": null,
      "DateStringPriority": 0,
      "DueDate": "",
      "Recurrence": null,
      "ItemOrder": null,
      "Priority": 4,
      "LastSyncedDateTime": "IGNORE",
      "Children": [],
      "DueDateTime": null,
      "CreatedDate": "IGNORE",
      "LastCheckedDate": null,
      "LastUpdatedDate": "IGNORE",
      "Deleted": false,
      "Notes": "",
      "InHistory": false,
      "SyncClientCreationId": null,
      "DueTimeSpecified": true,
      "OwnerId": "IGNORE"
    }
    """
    When yo envio una peticion GET al url http://todo.ly/api/items/Id_Item.json con json
    """
    """
    Then quiero que el codigo de respuesta sea 200
    And yo espero el response body sea
    """
   {
      "Id": Id_Item,
      "Content": "Item Nuevo",
      "ItemType": 1,
      "Checked": false,
      "ProjectId": null,
      "ParentId": null,
      "Path": "",
      "Collapsed": false,
      "DateString": null,
      "DateStringPriority": 0,
      "DueDate": "",
      "Recurrence": null,
      "ItemOrder": null,
      "Priority": 4,
      "LastSyncedDateTime": "IGNORE",
      "Children": [],
      "DueDateTime": null,
      "CreatedDate": "IGNORE",
      "LastCheckedDate": null,
      "LastUpdatedDate": "IGNORE",
      "Deleted": false,
      "Notes": "",
      "InHistory": false,
      "SyncClientCreationId": null,
      "DueTimeSpecified": true,
      "OwnerId": "IGNORE"
    }
    """
    When yo envio una peticion DELETE al url http://todo.ly/api/items/Id_Item.json con json
    """
    """
    Then quiero que el codigo de respuesta sea 200
    And yo espero el response body sea
    """
    {
      "Id": Id_Item,
      "Content": "Item Nuevo",
      "ItemType": 1,
      "Checked": false,
      "ProjectId": null,
      "ParentId": null,
      "Path": "",
      "Collapsed": false,
      "DateString": null,
      "DateStringPriority": 0,
      "DueDate": "",
      "Recurrence": null,
      "ItemOrder": null,
      "Priority": 4,
      "LastSyncedDateTime": "IGNORE",
      "Children": [],
      "DueDateTime": null,
      "CreatedDate": "IGNORE",
      "LastCheckedDate": null,
      "LastUpdatedDate": "IGNORE",
      "Deleted": false,
      "Notes": "",
      "InHistory": false,
      "SyncClientCreationId": null,
      "DueTimeSpecified": true,
      "OwnerId": "IGNORE"
    }