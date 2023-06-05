# US 015 - List all booking requests for properties

## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID                                                                     | Question: Which class is responsible for...                                              | Answer                       | Justification (with patterns)                                                                                                 |
|:-----------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|:-----------------------------|:------------------------------------------------------------------------------------------------------------------------------|
| Step 1 : asks to see all the booking requests made to properties manage by himself | ... interacting with the actor?                                                          | ListVisitsUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
|                                                                                    | ... coordinating the US?                                                                 | ListVisitsController         | Controller                                                                                                                    |
| Step 2 : requests data                                                             | ... displaying the UI for the actor to select data?                                      | ListVisitsUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
| Step 3 : selects requested data (begin date, end date)                             | ... validating selected data?                                                            | ListVisitsUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
|                                                                                    | ... saving the selected category?   ???                                                  | ...                          | ...                                                                                                                           |
|                                                                                    | ... obtaining the type of sorting algorithms                                             | AlgorithmRepository          | Information Expert: contains all the agencies; Pure Fabrication.                                                              |
|                                                                                    | ... fetching the necessary data to create an AlgorithmDto?                               | AlgorithmMapper              | Delegation/Pure Fabrication: promoting Request to specific class to ensure Low Coupling & High Cohesion.                      |
|                                                                                    | ... converting the original data of a algorithm to the DTO?                              | AlgorithmMapper              | Delegation/Pure Fabrication: promoting Request to specific class to ensure Low Coupling & High Cohesion.                      |
| Step 4 : shows sorting algorithm options and asks to select one                    | ... displaying the sorting algorithms                                                    | ListVisitsUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
| Step 5 : selects data (sorting algorithm)                                          | ... validating selected data?                                                            | ListVisitsUI?? or Algorithm? | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |
|                                                                                    | ... obtaining the agent email?                                                           | User Session                 | Information Expert: cf. User Authentication & Authorization component documentation.                                          |
|                                                                                    | ... obtaining the agency that has the agent with retrieved email?                        | AgencyRepository             | Information Expert: contains all the agencies; Pure Fabrication.                                                              |
|                                                                                    | ... evaluating if an agency has an agent with the retrieved email?                       | Agency                       | Information Expert: an agency knows all its agents (employees).                                                               |
|                                                                                    | ... fetching the visit requests/announcements of the agent that has the specified email? | AnnouncementList??           | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                    | ... verifying if the visit requests/announcements is from the specified email's agent.   | Announcement                 | Information Expert : knows their responsible agent.                                                                           |
|                                                                                    | ... obtaining the visit requests instance/list?                                          | Announcement                 | Information Expert : contains the OrderList.                                                                                  |
|                                                                                    | ... adding to a list the visits that belong to the agent?                                | AnnouncementList??           | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                    | ... sorting the visits according to sorting algorithm?                                   | AnnouncementList??           | Delegation/Pure Fabrication: promoting collection from Announcement to specific class to ensure Low Coupling & High Cohesion. |
|                                                                                    | ... fetching the necessary data to create an VisitDto?                                   | VisitMapper                  | DTO pattern                                                                                                                   |
|                                                                                    | ... converting the original data of a visit to the DTO?                                  | VisitMapper                  | DTO pattern                                                                                                                   |
| Step 6 : display all booking requests according to the dates and algorithm chosen  | ... displaying the visit requests                                                        | ListVisitsUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                 |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

[//]: # ( * Algorithm)
 * Agency
 * Announcement
 * Visit

Other software classes (i.e. Pure Fabrication) identified: 

 * ListVisitsUI 
 * ListVisitsController
 * Algorithm
 * AlgorithmMapper
 * AlgorithmDto
 * AnnouncementList
 * VisitMapper
 * VisitDto


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us015-sequence-diagram-full.svg)

### Alternative 2 - Split Diagram

This diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us015-class-diagram.svg)