# US 009 - Schedule a visit to a property

## 1. Requirements Engineering

### 1.1. User Story Description

As a client, I want to leave a message to the agent to schedule a visit to a property of my interest.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>	After consulting a list of properties, the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions.


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
> **Answer:** Yes.


> **Question:** Can the client make multiple schedules in a single iteration for different properties, or can they only have 1 property per iteration with multiple schedules?
>
> **Answer:** The client can only schedule a visit to one property at a time. Each time the client schedules a visit, the client should specify, for each day, one or multiple time slots. The time slots should not overlap.
> If the client wants to visit more properties, the client should use/run again the visit scheduling feature available in the system.


> **Question:** According to AC1, the list of available properties must be sorted from the most recent entries to the oldest. Assuming that this is done so the client can see the available properties in order to select one and given that in a previous question you've stated that the required information for the message should be typed then, for this US, is the only selected data the property that the client wishes to visit?
>
> **Answer:** System behavior must be consistent. For instance, the filters to be applied when the client is viewing a list of properties should be similar to the filters used in US1.


> **Question:** When writing the information to schedule a meeting with the agent, the preferred date and the time slot of the visit should be checked. The client can choose any day of the year? And any time of the day?
>
> **Answer:** Any date and time can be specified. We are always available to our customers!


> **Question:** On US9 AC1 it is says: "AC1. A list of available properties must be shown, sorted from the most recent entries to the oldest." Does this mean that we can only have the option to make contact in this type of sort?
>
> **Answer:** No. AC1 is the default sorting method.


> **Question:** When we list in another way, should not be possible to have the option to schedule a visit?
>
> **Answer:** After sorting the properties, the cliente can still schedule a visit.


> **Question:** When sending the message, is the visit immediately scheduled after being validated by the system, or is it necessary for the agent to approve it?
>
> **Answer:** No, the client is only making a visit request.


> **Question:** In AC2, when the suggestion of date and time is sent, is there a standard duration for the visit or is it mandatory to fill in a start time and an end time for the visit?
>
> **Answer:** There is no standard to schedule a visit. The client should define his availability specifying the start time and the end time for the visit. 


> **Question:** In AC2, can any time be used or are we limited to certain hours?
>
> **Answer:** Any time can be used.


> **Question:** Also in AC2, should we use the 12 am/pm or 24-hour time format?
>
> **Answer:** Use the 24-hour time format.



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

* List of existing property announcements available
* Sucess message when request is valid and registered in the system
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - US009](svg/us009-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* No relevant remarks.