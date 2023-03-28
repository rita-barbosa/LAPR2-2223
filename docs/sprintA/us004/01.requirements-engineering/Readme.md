# US 004 - To create a Task

## 1. Requirements Engineering

### 1.1. User Story Description

As an owner, I intend to submit a request for listing a property sale or rent,
choosing the responsible agent.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

Each request lists a property for sale or/and rent, and it is assigned to an agent.

The request for sale's standard information is the property's listing, the price/rent, the renting contract's duration (when applicable),
area (m<sup>2</sup>), location, distance from the City Center, and one or more photographs.

The request for sale's additional information is based on the property's type, as seen in the table below.

|     Information     |            House            | Apartment |
|:-------------------:|:---------------------------:|:---------:|
| Number of Bedrooms  |           0 or +            |  0 or +   |
| Number of Bathrooms |           0 or +            |  0 or +   |
|   Central Heating   |          Yes / No           | Yes / No  |
|  Air Conditioning   |          Yes / No           | Yes / No  |
|      Basement       |          Yes / No           |     -     |
|  Inhabitable Loft   |          Yes / No           |     -     |
|    Sun Exposure     | North / South / West / East |     -     |

Being an employee, the Agent's attributes are: name, citizen's card number, taxes number, address, email address, contact/telephone number, and the agency to which it is assigned.

[//]: # (As long as it is not announced, access to the request is exclusive to the owner and respectively assigned agent.)

**From the client clarifications:**

> **Question:** What are the attributes of the Owner?
>
> **Answer:**  The Owner's attributes are: name, citizen's card number, tax number, address, email address, and telephone number.


> **Question:** Does an Owner need to be registered in the system to submit a request for a property listing?
>
> **Answer:**  No. When making the request to list a property, the owner should introduce his own data (attributes + property's data).


> **Question:** Is the Owner the only actor that can submit a request and assign an agent?
>
> **Answer:**  No. The agency's personnel that registers the information in the system can choose to assign any agent.


> **Question:** If the Owner does not choose an agent, will the application/agency randomly assign an agent to the property?
>
> **Answer:**  No. All data slots must be filled.


> **Question:** Are there any restrictions on the choice of an agent?
>
> **Answer:**  No, the Owner can choose any agent.


> **Question:** Can an Agent work in more than one store?
>
> **Answer:** No.


> **Question:** Does each request have unique reference?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** Can the property's Owner summit requests to both sale and rental listings?
>
> **Answer:** YET TO BE CLARIFIED.


> **Question:** Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer:** No.


> **Question:** Does the Owner have a limit of request they can do?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** Can the Owner choose the agency/branch/store independently of the location of the property?
>
> **Answer:** Yes.


> **Question:** Does the request have a description? If so, does the description have any limitation?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** Is the agent essential for the submission of a request? If the owner does not choose any agent, will the platform randomly assign a respective agent?
>
> **Answer:** No, the selection of an Agent is necessary data for the request's submission.


> **Question:** Must all the request's data slots be filled, according to the property's type and listing?
> 
> **Answer:** Yes.


> **Question:** If the owner leaves the listing unfinished, can it be saved or stay as a sketch to be finished later?
>
> **Answer:** No.


* The created request stays in a "not published" state in order to distinguish from "published" requests. ???? (check **Moodle** - Mr. Client says that's an implementation detail, he doesn't need to know)

### 1.3. Acceptance Criteria

* **AC1:** Request reference must have at least *n* alphanumeric chars. ????
* **AC2:** When creating a request with an already existing reference/name, the system must reject such operation and the owner must have to change the new request.
* **AC3:** The currency used for the property's price or rent is US Dollars (USD - $)
* **AC4:** citizen's card number, taxes number, address, email address, and telephone number CRITERIA ??????

### 1.4. Found out Dependencies

* There is no dependencies to US004.

### 1.5 Input and Output Data

**Input Data:**
<p>

* Typed data:
    * a reference ???
    * a designation / name
    * price (Sale and/or Rent)
    * area ( m<sup>2</sup> )
    * location (VERY AMBIGUOUS)
    * a description ???
    * distance from the city center (DCC)
    * Owner's data
      * name
      * citizen's card number
      * tax number
      * address
      * email address
      * telephone number
    
</p>
<p>

* Selected data:
    * property's listing 
    * property's type
    * central heating
    * air conditioning
    * basement
    * number of Bedrooms
    * number of Bathrooms ????
    * inhabitable loft
    * sun exposure
    * agency
      * agent
</p>
<p>

* Unstructured data:
     * photographs
   
</p>

**Output Data:**

* List of request's listings
* List of property's types
* List of number of bedrooms
* List of existing/available agents
* (In)Success of the operation

[//]: # (* List of number of bathrooms)


### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

![System Sequence Diagram - US004](svg/us004-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks