# US 004 - To submit a Request for an Announcement

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID                                     | Question: Which class is responsible for...                            | Answer                  | Justification (with patterns)                                                                                 |
|:---------------------------------------------------|:-----------------------------------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1 : asks to create a new Request  	           | ... interacting with the actor?                                        | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                            | ... coordinating the US?                                               | CreateRequestController | Controller.                                                                                                   |
| 			  		                                            | ... instantiating a new request?                                       | Announcement            | Creator (Rule 1): in the DM, Announcement has an Request.                                                     |
| 			  		                                            | ... knowing the user using the system?                                 | UserSession             | IE: cf. A&A component documentation.                                                                          |
|                                                    |                                                                        | Owner                   | IE: knows its own data (e.g. email).                                                                          |
|                                                    | ... obtaining the business types?                                      | BusinessTypeRepository  | IE: the types of business are the same for all requests; Pure Fabrication.                                    |
| Step 2 : shows business types 		                   | ... displaying the business types?			                                  | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 3 : selects business type 		                  | ... temporarily keeping input data?                                    | CreateRequestUI         | IE: the types of property are the same for all requests; Pure Fabrication.                                    |
|                                                    | ... validating input data?                                             | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... obtaining the property types?                                      | BusinessTypeRepository  | IE: the types of business are the same for all requests; Pure Fabrication.                                    |
| Step 4 : shows property types 		                   | ... displaying the property types?	                                    | PropertyTypeRepository  | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 5 : selects property type 		                  | ... temporarily keeping input data?                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... validating input data?                                             | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 6 : requests data                             | ... displaying the UI for the actor to input data?                     | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 7 : types requested data                      | ... validating input data?                                             | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... temporarily keeping input data?                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 8 : requests data                             | ... displaying the UI for the actor to input data?                     | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 9 : selects requested data                    | ... validating input data?                                             | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... temporarily keeping input data?                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... obtaining the agencies list?                                       | AgencyRepository        | IE: one of all agencies can be chosen for all requests; Pure Fabrication.                                     |
| Step 10 : shows agencies 		                        | ... displayingthe agencies list?	                                      | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 11 : selects an agency 		                     | ... temporarily keeping input data?                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... validating input data?                                             | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                    | ... obtaining the agents list?                                         | Agency                  | IE: knows all its agents.                                                                                     |
| Step 12 : shows agents list of choosen agency      | ... displaying the property types?	                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 13 : selects an agent 		                      | ... temporarily keeping input data?                                    | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 14 : shows all data and requests confirmation | ... display all the information before submitting?                     | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 15 : submits data                             | ... creating the Request object?                                       | Agency                  | Creator (Rule 1): in the DM, Agency has a Request.                                                            |
|                                                    | ... validating the data locally (mandatory data)?                      | Request                 | IE: owns its data.                                                                                            |
| Step 16 : create a new property                    | ... instantiating a new Property?                                      | Request                 | Creator (Rule 1): in the DM, Request has Property.                                                            |
|                                                    | ... validating local data?                                             | Property                | IE: owns its data.                                                                                            |
| Step 17 : create a new location                    | ... instantiating a new Location?                                      | Property                | Creator (Rule 1): in the DM, Request has Property.                                                            |
|                                                    | ... validating local data?                                             | Location                | IE: owns its data.                                                                                            |
|                                                    | ... adding to a collection and globally validating duplicated records? | Agency                  | Creator (Rule 3): in the DM, Property closely uses Location.                                                  |
| Step 18 : displays operation success               | ... informing operation success?                                       | CreateRequestUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Request
* Agency
* Property
* Location

Other software classes (i.e. Pure Fabrication) identified:

* CreateRequestUI
* CreateRequestController

## 3.2. Sequence Diagram (SD)

### Split Diagram

This diagram shows the sequence of interactions between the classes involved in the realization of this user story. It
is split in partial diagrams to better illustrate the interactions between the classes.

It uses interaction ocurrence.

![Sequence Diagram - split](svg/us004-sequence-diagram-slip.svg)

**Get Business Type List - Partial SD**

![Sequence Diagram - Partial - Get Business Type List](svg/us004-sequence-diagram-partial-get-business-type-list.svg)

**Get Business Type Object - Partial SD**

![Sequence Diagram - Partial - Get Business Type Object](svg/us004-sequence-diagram-partial-get-business-type-object.svg)

**Get Property Type List - Partial SD**

![Sequence Diagram - Partial - Get Property Type List](svg/us004-sequence-diagram-partial-get-property-type-list.svg)

**Get Property Type Object - Partial SD**

![Sequence Diagram - Partial - Get Property Type Object](svg/us004-sequence-diagram-partial-get-property-type-object.svg)

**Add Available Equipment Description - Partial SD** 

![Sequence Diagram - Partial - Add Available Equipment](svg/us004-sequence-diagram-partial-add-available-equipment-description.svg)

**Add Photograph Uri - Partial SD**

![Sequence Diagram - Partial - Add Photograph](svg/us004-sequence-diagram-partial-add-photograph-uri.svg) 

**Get Agencies List - Partial SD**

![Sequence Diagram - Partial - Get Agencies List](svg/us004-sequence-diagram-partial-get-agency-list.svg)

**Get Agents List - Partial SD**

![Sequence Diagram - Partial - Get Agents List](svg/us004-sequence-diagram-partial-get-agents-list.svg)

**Get Owner Email - Partial SD**

![Sequence Diagram - Partial - Get Owner Email](svg/us004-sequence-diagram-partial-get-owner-email.svg)

**Create Request - Partial SD**

![Sequence Diagram - Partial - Create Request](svg/us004-sequence-diagram-partial-create-request.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us004-class-diagram.svg)