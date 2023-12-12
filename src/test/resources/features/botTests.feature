Feature: xbot

  Background: Log in account
    Given Launch browser
    When Navigate to "url"
    And Click on login button

  @xbot1
  Scenario Outline: It sends tag and message
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Enter "<tag>", "<message>" and send a message "<Number of Tweets>"
    Then close the page
    Examples:
      | username | password  | tag       | message        | Number of Tweets |
      | fourthUN | fourthPSW | MusterTag | Muster Message | 1                |


  @xbot2
  Scenario Outline: It sends tag, message and text from excel
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Enter "<tag>", "<link>" and send the message "<Number of Tweets>" times
    Then close the page
    Examples:
      | username | password  | tag       | link           | Number of Tweets |
      | firstUN  | firstPSW  | MusterTag | Muster link | 1                |
      | secondUN | firstPSW  | MusterTag | Muster link | 1                |
      | fourthUN | fourthPSW | MusterTag | Muster link | 1                |


  @xbot3
  Scenario Outline: It sends text from excel and add account
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Enter "<tag>", "<link>" and add account send the message "<Number of Tweets>" times
    Then close the page
    Examples:
      | username | password  | tag       | link           | Number of Tweets |
      | firstUN  | firstPSW  | MusterTag | Muster link | 1                |
      | secondUN | firstPSW  | MusterTag | Muster link | 1                |
      | fourthUN | fourthPSW | MusterTag | Muster link | 1                |


  @xbot4
  Scenario Outline: It sends text from add an account and use array text
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Use arrays for tag, text and link and send the message "<Number of Tweets>" times
    Then close the page
    Examples:
      | username | password  | Number of Tweets |

      | fourthUN | fourthPSW | 2                |
      | thirdUN  | thirdPSW  | 2                |
      | secondUN | secondPSW | 2                |



  @xbot5
  Scenario Outline: It sends text from excel, makes a mention, add a photo or a viedo
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Enter "<tag>", "<link>" and send the message "<limit>" times and add a video
    Then close the page
    Examples:
      | username | password  | tag       | link        | limit |
      | firstUN  | firstPSW  | MusterTag | Muster link | 1     |
      | secondUN | firstPSW  | MusterTag | Muster link | 1     |
      | fourthUN | fourthPSW | MusterTag | Muster link | 1     |


  @xbot6
  Scenario Outline: It sends tag, link, message from excel and accounts as sublist and add a photo or video
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Create sublist acording to "<sublist element numbers>",  enter "<tag>", "<link>" and send the message "<Number of Tweets>" times
    Then close the page

    Examples:
      | username | password  | sublist element numbers | tag | link | Number of Tweets |
      | secondUN | secondPSW | 2                       | tag | link | 5                |
      | thirdUN  | thirdPSW  | 2                       | tag | link | 5                |
      | fourthUN | fourthPSW | 2                       | tag | link | 5                |

  @xbot7
  Scenario Outline: It uses sublists for accounts and array for links, tag and text
    And Enter username as "<username>"
    And Enter password as "<password>"
    And Click cookies
    And Create sublist acording to "<sublist element numbers>", use arrays for tag, text and link and send the message "<Number of Tweets>" times
    Then close the page

    Examples:
      | username | password  | sublist element numbers | Number of Tweets |
      | secondUN | secondPSW | 5                       | 2                |
      | thirdUN  | thirdPSW  | 5                       | 2                |
      | fourthUN | fourthPSW | 5                       | 2                |


