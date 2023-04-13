# US 007 - To register user in the system 

## 1. Requirements Engineering


### 1.1. User Story Description


As an unregistered user, I want to register in the system to buy, sell or rent properties.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	All those who wish to use the application must be authenticated. 


>	??? 



**From the client clarifications:**

> **Question:** Does the user also receive the password via email or can he choose a password when registering?
>  
> **Answer:** YET TO BE ANSWERED


> **Question:** When an unregistered user wants to register a new account in the system, the set of parameters that are asked are the following: name, citizen card number, tax number, email, phone number, and password. Do you want any extra parameters/requirements to be asked or just the ones specified above? If so, which ones are mandatory? 
>  
> **Answer:** YET TO BE ANSWERED


### 1.3. Acceptance Criteria


* **AC1:** Passwords have seven alphanumeric characters in length, including three capital letters and two digits.



### 1.4. Found out Dependencies


* There is a dependency to "US003 Create a task category" since at least a task category must exist to classify the task being created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
   * name
   * citizen's card number
   * taxes number
   * address
   * email address
   * telephone number
	
* Selected data:
	* Classifying task category 


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**


![System Sequence Diagram - US007](svg/us007-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.