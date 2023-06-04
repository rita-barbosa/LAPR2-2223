# US 018 - To analyze sale deals of houses and apartments

## 3. Design - User Story Realization

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                                                 | Question: Which class is responsible for...                                             | Answer                                     | Justification (with patterns)                                                                                 |
|:---------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------|:-------------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1: asks to analyze property deals.                                                                        | ... interacting with the actor?                                                         | AnalyzeDealsUI                             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	                                                                                                              | ... coordinating the US?                                                                | AnalyzeDealsController                     | Controller                                                                                                    |
|                                                                                                                | ... obtaining the type of regression models list?                                       | RegressionModelsTypeRepository             | **VERIFY IF ITS NECESSARY TO REALLY DO THIS**                                                                 |
|                                                                                                                | ... fetching the necessary data to create an AnnouncementDto?                           | RegressionModelTypeMapper                  | DTO pattern  **VERIFY IF ITS NECESSARY TO REALLY DO THIS**                                                    |
|                                                                                                                | ... converting the original data of an announcement to the DTO?                         | RegressionModelTypeMapper                  | DTO pattern  **VERIFY IF ITS NECESSARY TO REALLY DO THIS**                                                    |
| Step 2: shows regression models available and asks to select one.                                              | ... displaying the regression model types?                                              | AnalyzeDealsUI                             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 3: selects regression model.                                                                              | ... saving the selected data?                                                           | AnalyzeDealsUI                             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 4: shows the parameters available for regression models and asks to select one.	                          | ... displaying the parameters available?                                                | AnalyzeDealsUI                             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 5: selects independent variable.                                                                          | ... saving the selected parameter?                                                      | AnalyzeDealsUI                             | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                                                                                | ... obtaining the RegressionModelType instance from the selected RegressionModelTypeDto | RegressionModelTypeMapper                  | DTO pattern  **VERIFY IF ITS NECESSARY TO REALLY DO THIS**                                                    |
|                                                                                                                | ... getting the designation of the RegressionModelTypeDto?                              | RegressionModelTypeDto                     | Information Expert: knows its own data; Dto pattern **VERIFY IF ITS NECESSARY TO REALLY DO THIS**             |
|                                                                                                                | ... fetching all the announcement data present in the system?                           | AgencyRepository                           | Information Expert: knows all the registered agencies.                                                        |
|                                                                                                                | ... obtaining data of each agency announcements?                                        | AnnouncementList                           | Information Expert: knows all the announcement instances of an agency.                                        |
|                                                                                                                | ... verifying if the announcement is a deal?                                            | Announcement                               | Information Expert: has the necessary information to validate this criteria.                                  |
|                                                                                                                | ... verifying if its a sale of a house or apartment announcement?                       | Request                                    | **CHECK THIS STEP**                                                                                           |
|                                                                                                                | ... getting the data of an announcement after its validation?                           | Announcement                               | Information Expert: knows its own data.                                                                       |
|                                                                                                                | ... calculating all the necessary statistics?                                           | **CLASSES THAT IMPLEMENT RegressionModel** |                                                                                                               |
|                                                                                                                |                                                                                         |                                            |                                                                                                               |
|                                                                                                                |                                                                                         |                                            |                                                                                                               |
| Step 6: shows the estimated price for each house and apartment deal, and the selected regression model report. |                                                                                         |                                            |                                                                                                               |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Organization
* Task

Other software classes (i.e. Pure Fabrication) identified:

* CreateTaskUI
* CreateTaskController

## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)