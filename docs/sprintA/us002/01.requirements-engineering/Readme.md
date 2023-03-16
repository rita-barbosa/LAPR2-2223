# US 002 - To publish an announcement

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I can publish any sale announcement on the system, for
example received through a phone call.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Each announcement must have the property characteristics and price. Depending on the type of property there will be more or less attributes to be mentioned.


>   The information required in any kind of sale is: type of property, area, location, DCC, price and one or maore photographs. If the property is an apartment or house then the number of bedrooms, bathrooms and parking spaces, as well as the available equipment should be included. Lastly, if it's a house the existence of a basement, inhabitable loft and sun exposure must also be mentioned. 



**From the client clarifications:**

> **Question:** How is the sun exposure evaluated?
>  
> **Answer:** *wainting for answer*


### 1.3. Acceptance Criteria


* **AC1:** All required information must be filled in.


### 1.4. Found out Dependencies


* There is a dependency to "US004 Submit a request for listing a property" since at least a request for an announcement must be made for the agent to be able to publish an advertisement.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* area in m ^2^ 
	* location
	* DCC
	* price of the property
	* one or more photographs
    * number of bedrooms
    * number of bathrooms
    * number of parking spaces
    * available equipment
    * existence of basement
    * inhabitable loft
    * sun exposure

* Selected data:
	* Type of property


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us002-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* All registered information, except the agency commission, can be accessed by the client who intends to
  buy or rent the property.