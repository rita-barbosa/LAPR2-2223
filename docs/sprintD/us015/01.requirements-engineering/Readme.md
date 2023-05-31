# US 015 - List all booking requests for properties

## 1. Requirements Engineering


### 1.1. User Story Description


As an agent, I intend to list all booking requests for properties managed by me.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	After consulting a list of properties, the client can request to schedule a visit to the real estate agent
for a specific property to verify its conditions. The agent receives the request, checks the
availability and sends the response.


**From the client clarifications:**

> **Question:** Booking is a slightly vague word. Our team wants clarifications on what type of requests the word encompasses (visit requests being our strongest guess, but we are not sure).
>  
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:** Are the "booking requests" mentioned in the US015 description the same thing as the "message [...] to schedule a visit to a property of my interest" in US009? Or are they different requests entirely?
>  
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:** US015's AC1 states that "The list of requests must be shown for a specific period (begin date, end date)". As such, our team would like to know if you want this time period to be selected or typed, and in which format it should be in.
>
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:** US015's AC2 states that "The list of requests must be sorted by date in ascending order. The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available."
>
>As such, the team would like to know if this requirement will be part of the user's interaction with this US, or if it's just there for flexibility purposes. By that we mean to ask if the client will be able to chose which sorting method they want to apply, even if the end result will always be the same, or if we will provide those methods but chose which one is actually going to sort the list, without the client's awareness, as all they did was ask to see the list and the program showed them the end result.
>
>If we are to chose which one of the methods will be used, should we chose the most efficient one? It seems like a no brainer question, but we would still like to make sure we are following your vision for the program.
>
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:** In the AC2, what do you mean by "The sorting algorithm to be used by the application must be defined through a configuration file."? Does the user select which algorithm to use?
>
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:** In US015's AC1, it is stated that "The list of requests must be shown for a specific period (begin date, end date)."
>
>This time period caused confusion, as the team is unsure whether the "date" is referring to the date chosen by the client in the message (See US009's requirements) or the date of creation of the visit request.
>
> **Answer:** **_WAITING FOR AN ANSWER_**


> **Question:**
>
> **Answer:**


### 1.3. Acceptance Criteria


* **AC1:** The list of requests must be shown for a specific period (begin date, end date).
* **AC2:** The list of requests must be sorted by date in ascending order. The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available.

### 1.4. Found out Dependencies


* There is a dependency to "US009 Schedule a visit to a property" since at least a schedule visit should be made to see the booking list.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a being date 
	* an end date
	
* Selected data:
	* algorithm 


**Output Data:**

* List of existing booking requests
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram](svg/us015-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks
