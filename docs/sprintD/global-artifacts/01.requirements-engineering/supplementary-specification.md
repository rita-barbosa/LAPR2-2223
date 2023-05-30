# Supplementary Specification (FURPS+)

## Functionality

* Authentication
    * "All those who wish to use the application must be authenticated with a password (...)"
  

* Security
    * "All those who wish to use the application must be authenticated(...)"
    * "(...) a password holding seven alphanumeric characters, including three capital letters and two digits."
  

* Scheduling
    * "...the client can request to schedule a visit to the real estate agent for a specific property to verify its conditions."


* Reporting
    * "(...) the agent records the visit and an indication of whether or not he thins the deal will take place." 



* Printing
    * "All registered information (...) can be accessed by the client (...)"


* Registration
    * "The company's systems administrator will be responsible for registering all employees (...) and branches of the network (...)"


* Communication
    * "The owner (...) sends the request to an agent."
    * "(...)the client can request to schedule a visit to the real estate agent (...)"
    * "the client (...) sends a request for the purchase/lease of the property to the agent"
    * "The agent receives the request (...) and sends the response."


* Analyzing
    * "The main functions of a store manager are to monitor and streamline the branch (...) and to analyse and evaluate the performance of employees."
    * "The manager of the network intends to analyse the performance of each of the branches(...)"


* Localization
    * "The application must support the English language"


* System Management
    * "The company's systems administrator will be (...) preparing the system in order to facilitate the insertion of advertisements and facilitate the use of the application"



## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

* Aesthetics 
    * "The application graphical interface is to be developed in JavaFX 11."


* Accessibility
    * "(...) the client is, then responsible for being able to consult the properties by type, number of rooms, and sort by criteria such as price or the parish where the property is located."


* Documentation
    * "(...) use Javadoc to generate useful documentation for Java code."<br>"The JaCoCo plugin should be used to generate the coverage report"

    
## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are:
frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between
failures._

* Recoverability
    * "The application should use object serialization to ensure data persistence between two runs of the application"

  
## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._

* Testability
    * "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations"
    * "The units tests should be implemented using the JUnit 5 framework."


## +

**Section's Description**

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process,
mandatory standards/patterns, use of development tools, class library, etc._

* Programming languages
    * "The application must be developed in Java language using IntelliJ IDE or NetBeans."

* Use of development tools
    * "Use Javadoc to generate useful documentation for Java code."
    * "The unit tests should be implemented using the JUnit 5 framework."
    * "The JaCoCo plugin should be used to generate the coverage report."
    * "All the images/figures produced during the software development process should be recorded in SVG format."


* Software process
    * "Adopt best practices for identifying requirements and for OO software analysis and design."  


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

* Implementation language
    * "The application must be developed in Java language using IntelliJ IDE or NetBeans."
    * "The application graphical interface is to be developed in JavaFX 11."


* Mandatory standards/patterns
    * "Adopt recognized coding conventions and standards (e.g., CamelCase)"
    * "The application should use object serialization to ensure data persistence between two runs of the application."

  