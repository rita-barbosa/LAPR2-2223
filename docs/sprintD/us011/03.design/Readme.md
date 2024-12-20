# US 011 - To accept purchase orders

## 3. Design - User Story Realization

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                                                        | Question: Which class is responsible for...                                  | Answer                | Justification (with patterns)                                                                                                 |
|:----------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------|:----------------------|:------------------------------------------------------------------------------------------------------------------------------|
| Step 1: asks to accept purchase orders                                                                                | ... interacting with the actor?                                              | AcceptOrderUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
|                                                                                                                       | ... coordinating the US?                                                     | AcceptOrderController | Controller                                                                                                                    |
|                                                                                                                       | ... obtaining the agent email?                                               | User Session          | Information Expert: cf. User Authentication & Authorization component documentation.                                          |
|                                                                                                                       | ... obtaining the agency that has the agent with retrieved email?            | AgencyRepository      | Information Expert: contains all the agencies; Pure Fabrication.                                                              |
|                                                                                                                       | ... evaluating if an agency has an agent with the retrieved email?           | Agency                | Information Expert: an agency knows all its agents (employees).                                                               |
|                                                                                                                       | ... fetching the announcements of the agent that has the specified email?    | AnnouncementList      | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                                                       | ... verifying if the announcement has the specified email's agent.           | Announcement          | Information Expert : knows their responsible agent.                                                                           |
|                                                                                                                       | ... obtaining the OrderList instance?                                        | Announcement          | Information Expert : contains the OrderList.                                                                                  |
|                                                                                                                       | ... sorting the orders by highest order amount?                              | OrderList             | Delegation/Pure Fabrication: promoting collection from Order to specific class to ensure Low Coupling & High Cohesion.        |
|                                                                                                                       | ... saving (adding to a list) the announcements that belong to the agent?    | AnnouncementList      | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                                                       | ... sorting the announcements by oldest acceptance (publishing) date?        | AnnouncementList      | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                                                       | ... fetching the necessary data to create an AnnouncementDto?                | AnnouncementMapper    | DTO pattern                                                                                                                   |
|                                                                                                                       | ... converting the original data of an announcement  to the DTO?             | AnnouncementMapper    | DTO pattern                                                                                                                   |
|                                                                                                                       | ... fetching the necessary data to create an OrderDto?                       | OrderMapper           | DTO pattern                                                                                                                   |
|                                                                                                                       | ... converting the original data of an order to the DTO?                     | OrderMapper           | DTO pattern                                                                                                                   |
| Step 23: displays property by oldest and its orders by highest price\n and asks to select acceptanceAnswer for orders | ... displaying the properties and offers?                                    | AcceptOrderUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
| Step 24: selects acceptanceAnswer                                                                                     | ... saving the selected answer?                                              | AcceptOrderUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
| Step 26: submits data                                                                                                 | ... retrieving the agency that owns announcement with specified ID?          | AgencyRepository      | Information Expert: contains all the agencies; Pure Fabrication.                                                              |
|                                                                                                                       | ... verifying if the agency has an announcement with the ID?                 | AnnouncementList      | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                                                       | ... getting the announcement instance with specified ID?                     | AnnouncementList      | Information Expert: knows its own announcements.                                                                              |
|                                                                                                                       | ... verifying if the announcement has the specified ID?                      | Announcement          | Information Expert: knows its own information.                                                                                |
|                                                                                                                       | ... defining the order acceptance to the agent answer?                       | Order                 | Information Expert : knows its own data.                                                                                      |
|                                                                                                                       | ... sending a notification to the client informing about the agent decision? | Order                 | Information Expert: has the necessary data to send notification (create file).                                                |
| Step 33: displays operation success                                                                                   | ... informing operation success?                                             | AcceptOrderUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Announcement
* Agency
* Order

Other software classes (i.e. Pure Fabrication) identified:

* AcceptOrderUI
* AcceptOrderController
* AnnouncementList
* OrderList
* AnnouncementMapper
* AnnouncementDto
* OrderMapper
* OrderDto

## 3.2. Sequence Diagram (SD)

### Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story,
but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction occurrence.

![Sequence Diagram - Split](svg/us011-sequence-diagram-split.svg)

**Get Agent Email**

![Sequence Diagram - Partial - Get Agent Email](svg/us011-sequence-diagram-partial-get-agent-email.svg)

**Get Announcements List**

![Sequence Diagram - Partial - Get Announcements List](svg/us011-sequence-diagram-partial-get-announcements-list.svg)

**Create AnnouncementDto**

![Sequence Diagram - Partial - Create AnnouncementDto](svg/us011-sequence-diagram-partial-create-announcementDto.svg)

**Get Announcement**

![Sequence Diagram - Partial - Get Announcement](svg/us011-sequence-diagram-partial-get-announcement.svg)

**Define Acceptance Answer**

![Sequence Diagram - Partial - Define Acceptance Answer](svg/us011-sequence-diagram-partial-define-acceptance.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us011-class-diagram.svg)