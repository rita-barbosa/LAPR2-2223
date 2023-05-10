# US 009 - Schedule a visit to a property

## 1. Requirements Engineering

### 1.1. User Story Description

As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>	After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions.

>	The agent receives the request, checks the availability and sends the response. If the agent accepts the order, it is automatically scheduled in the system.


**From the client clarifications:**

> **Question:** Does the client provide (by typing) their name and phone number for the message, regardless of whether that information is already available to the system?
>  
> **Answer:** The information available in the system should be used. The client does not need to type the name and phone number.


> **Question:** Is all the required data for the message typed, or is any of it selected?
>  
> **Answer:** For now the information should be typed.


> **Question:** Are the visits done to a property only made by the agent responsible for it?
>
> **Answer:** Yes.


> **Question:** Can the customer visit the same property more than once?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** In the message what is the characteristic to identify the property to visit? Can we use the location?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** When sending the message, is the visit immediately scheduled after being validated by the system, or is it necessary for the agent to approve it?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** In AC2, when the suggestion of date and time is sent, is there a standard duration for the visit or is it mandatory to fill in a start time and an end time for the visit?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** In AC2, can any time be used or are we limited to certain hours?
>
> **Answer:** YET TO BE ANSWERED.


> **Question:** Also in AC2, should we use the 12 am/pm or 24-hour time format?
>
> **Answer:** YET TO BE ANSWERED.


### 1.3. Acceptance Criteria

* **AC1:** A list of available properties must be shown, sorted from the most recent entries to the oldest.
* **AC2:** The message must also include the client's name, phone number, preferred date and time slot (from x hour to y hour) for the property visit.
* **AC3:** A client may post multiple visit requests, but only if those do not overlap each other.
* **AC4:** The client must receive a success message when the request is valid and registered in the system.

### 1.4. Found out Dependencies

* There is a dependency to "US002 Publish an announcement" and "US008 List property announcement requests", because a visit can only happen when an announcement is published.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
	* the visit's date
    * the visit's beginning hour 
	* the visit's end hour

**Output Data:**

* List of existing property announcements
* Sucess message when request is valid and registered in the system
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us009-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* No relevant remarks.