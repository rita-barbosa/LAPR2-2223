# US 004 - To create a Task

## 1. Requirements Engineering

### 1.1. User Story Description

As an owner, I intend to submit a request for listing a property sale or rent,
choosing the responsible agent.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> Each request lists a property for sale or/and rent, and it is assigned to an agent.

> The request for sale's standard information is the property's price, area ( m<sup>2</sup> ), location, distance from City
> Center, and one or more photographs.

> The request for sale's additional information is based on the property's type. As seen in the table below.
>> IS THERE ANY SPECIAL INFORMATION TO DISCLOSE FOR RENTAL REQUEST???

|     Information     |  House   | Apartment |
|:-------------------:|:--------:|:---------:|
| Number of Bedrooms  | 0 or +   | 0 or +    |
| Number of Bathrooms |  0 or +  |  0 or +   |
|   Central Heating   | Yes / No | Yes / No  |
|  Air Conditioning   | Yes / No | Yes / No  |
|      Basement       | Yes / No |     -     |
|  Inhabitable Loft   | Yes / No |     -     |
|    Sun Exposure     | Yes / No |     -     |

> As long as it is not announced, access to the request is exclusive to the owner and respectively assigned agent.
> >WHAT ABOUT ADMINISTRATOR AND ANYBODY ELSE WHO IS ABOVE, COMPANY-WISE, OF THE AGENT???

**From the client clarifications:**

> **Question:** If the owner does not choose an agent, will the application randomly assign an agent to the property?
>
> **Answer:** YET TO BE ANSWERED.

> **Question:** Does each request have unique reference?
>
> **Answer:** YET TO BE ANSWERED.

> **Question:** Can the property's owner summit requests to both sale and rental listings?
>
> **Answer:** YET TO BE ANSWERED.

> **Question:** Does the owner have a limit of request they can do?
>
> **Answer:** YET TO BE ANSWERED.

### 1.3. Acceptance Criteria

* **AC1:** All required information slots must be filled in.
* **AC2:** Request reference must have at least *n* alphanumeric chars. ????
* **AC3:** When creating a request with an already existing reference/name, the system must reject such operation and the owner
  must have to change the new request.
>DOES THE REQUEST HAVE A DESCRIPTION? IF SO, DOES THE DESCRIPTION HAVE ANY LIMITATION?

### 1.4. Found out Dependencies

* There is no dependency to US004.
* The request is dependent on the existence of an agent. ?????????? 
* To summit a request, the owner must be a registered user in the platform. ???

> IS THE AGENT ESSENTIAL FOR THE SUBMISSION OF A REQUEST? IF THE OWNER DOES NOT CHOOSE ANY AGENT, WILL THE PLATFORM RANDOMLY ASSIGN A RESPECTIVE AGENT???

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference ???
    * a designation / name
    * price (Sale and/or Rent)
    * area ( m<sup>2</sup> )
    * location
    * a description ???
    * distance from city center
    * number of Bedrooms
    * number of Bathrooms


* Selected data:
    * classifying property's type
    * central heating
    * air conditioning
    * basement
    * inhabitable loft
    * sun exposure
    * agent


* Imported/Uploaded data:
  * photographs

**Output Data:**

* List of existing property categories
* List of existing/available agents
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created request stays in a "not published" state in order to distinguish from "published" requests. ????