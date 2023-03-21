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
> **Answer:** Sun exposure will take the following values: North, South, East, or West.

> **Question:** Is the phone call the only way the agent can receive the sale announcement?
>
> **Answer:** *wainting for answer*

> **Question:** What are the minimum and maximum values of the discount range?
>
> **Answer:** *wainting for answer*

> **Question:** What are the ways to receive an alert for a discount?
>
> **Answer:** *wainting for answer*

> **Question:** When discounts are applied, is it just to one property or to many properties?
>
> **Answer:** *wainting for answer*


### 1.3. Acceptance Criteria

* **AC1:** The type of property must be specified.
* **AC2:** All the information describing a property has to be introduced.
* **AC3:** The area of the property needs to be in m<sup>2</sup>.


### 1.4. Found out Dependencies

* There is a dependency to "US004 Submit a request for listing a property" since at least a request for an announcement must be made for the agent to be able to publish an advertisement.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* Area of the property
	* Location
	* DCC
	* Price of the property
	* One or more photographs
    * Number of bedrooms
    * Number of bathrooms
    * Number of parking spaces
    * Available equipment
    * Existence of basement
    * Inhabitable loft
    * Sun exposure

* Selected data:
	* Type of property


**Output Data:**

* List of types of properties
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - US002](svg/us002-system-sequence-diagram.svg)

**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* All registered information, except the agency commission, can be accessed by the client who intends to
  buy or rent the property.