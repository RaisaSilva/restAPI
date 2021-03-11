Feature: ProjectToken

  @Item
  Scenario: As an admin user
            I want to create a project with a token
            So that I probe the project is created correctly

    Given Access to Todo.ly with email "upb2021@upb.com" and password 12345
    When I sent a GET request to get token at https://todo.ly/api/authentication/token.json
    Then My status code must be 200 (Ok)
    And I get the TokenString and I save it in TOKEN

    When I sent a POST request for creation of a new project at http://todo.ly/api/projects.json with TOKEN
    """
    {
      "Content": "Project Cesar con tokenAuth",
      "Icon": 3
    }
    """
    Then My status code must be 200 (Ok)
    And My project content must be
    """
    {
    "Id": "IGNORE",
    "Content": "Project Cesar con tokenAuth",
    "ItemsCount": 0,
    "Icon": 3,
    "ItemType": 2,
    "ParentId": null,
    "Collapsed": false,
    "ItemOrder": "IGNORE",
    "Children": [],
    "IsProjectShared": false,
    "ProjectShareOwnerName": null,
    "ProjectShareOwnerEmail": null,
    "IsShareApproved": false,
    "IsOwnProject": true,
    "LastSyncedDateTime": "IGNORE",
    "LastUpdatedDate": "IGNORE",
    "Deleted": false,
    "SyncClientCreationId": null
    }
    """