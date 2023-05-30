# US 017 - To list all deals made

## 1. Requirements Engineering

### 1.1. User Story Description

As a network manager, I want to list all deals made.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The set of stores is managed by a store network
manager. The main functions of a store manager are to monitor and streamline the branch with the
aim of getting to know better the business carried out and to analyse and evaluate the performance
of employees.
The manager of the network intends to analyse the performance of each of the branches and the
global behaviour of the network on a daily basis.
> 
Each task is characterized by having a unique reference per organization, a designation, an informal and a technical
> description, an estimated duration and cost as well as the its classifying task category.


> As long as it is not published, access to the task is exclusive to the employees of the respective organization.



**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.


> **Question:** Monetary data is expressed in any particular currency?
>
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POTs (virtual currency internal to the
> platform).

### 1.3. Acceptance Criteria

* **AC1:** The actor should be able to sort all properties by property area (square feet) in descending/ascending order.
* **AC2:** Two sorting algorithms should be implemented (to be chosen manually by the network manager).
* **AC3:** Worst-case time complexity of each algorithm should be documented in the
  application user manual that must be delivered with the application (in the annexes, where algorithms should be
  written in pseudocode).

### 1.4. Found out Dependencies

* There is a dependency to "US002 Publish an announcement" since at least an announcement must be published for a deal
  to be made.
* There is a dependency to "US010 To submit a purchase order" since at least a purchase request must be done for a deal
  to be made.
* There is a dependency to "US011 Accept purchase orders" since at least a purchase request must be accepted for a deal
  to be made.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference,
    * a designation,
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost

* Selected data:
    * Classifying task category

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Other alternatives might exist.**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.