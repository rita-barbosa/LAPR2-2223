# US 001 - To display listed properties 

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                                               | Question: Which class is responsible for...     | Answer                      | Justification (with patterns)                                                                                                       |
|:-------------------------------------------------------------------------------------------------------------|:------------------------------------------------|:----------------------------|:------------------------------------------------------------------------------------------------------------------------------------|
| Step 1 : asks to display listed properties                                                                   | 	... interacting with the actor?                | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| 	                                                                                                            | 	... coordinating the US?                       | DisplayPropertiesController | Controller                                                                                                                          |
| 	                                                                                                            | 	... obtaining listed properties?               | Agency                      | Creator (Rule 1): in the DM Agency has Announcement.                                                                                |
|                                                                                                              | ... obtaining filters?                          | FiltersRepository           | IE / Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. It has the data. |
| Step 2 : display listed properties by most recent added and asks to select data (type of business)           | 	...display listed properties?                  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
|                                                                                                              | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 3 : selects data (type of business)                                                                     | 	...validating selected data?                   | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 4 : asks to select data (type of property)                                                              | 	...display the UI for the actor to input data? | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 5 : selects data (type of property)                                                                     | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 6 : asks to select data (number of bedrooms)                                                            | 	...display the UI for the actor to input data? | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 7 : selects data (number of bedrooms)                                                                   | 	...validating selected data?                   | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |              
| Step 8 : display all the listed properties, according to the criteria chosen and asks to select data (price) | 	...display listed properties?                  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       | 
| 	                                                                                                            | 	... saving the created task?                   | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       | 
| Step 9 : selects data (price)   	                                                                            | 	...validating selected data?                   | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       | 
| Step 10 : asks to select data (type of sorting)                                                              | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 11 : select data (type of sorting)                                                                      | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 12 : asks to select data (city)                                                                         | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 13 : selects data (city)                                                                                | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 14 : asks to select data (type of sorting)                                                              | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 15 : select data (type of sorting)                                                                      | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 16 : asks to select data (state)                                                                        | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 17 : selects data (state)                                                                               | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 18 : asks to select data (type of sorting)                                                              | ...display the UI for the actor to input data?  | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 19 : select data (type of sorting)                                                                      | ...validating selected data?                    | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| Step 20 : display all the listed properties, according to the criteria chosen                                | ...display listed properties?                   | DisplayPropertiesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Agency

Other software classes (i.e. Pure Fabrication) identified: 

 * DisplayPropertiesUI  
 * DisplayPropertiesController


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

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

![Class Diagram](svg/us006-class-diagram.svg)