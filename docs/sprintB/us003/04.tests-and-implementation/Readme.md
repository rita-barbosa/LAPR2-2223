# US 003 - To register a new employee

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the RegisterEmployee class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		RegisterEmployee instance = new Employee (null, null, null, null, null, null, null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class RegisterEmployeeController 

```java
public class registerEmployee(roleRepository, agencyRepository) {
    
	Agency agency = getAgencyRepository();
    Role role = getRoleRepository();

	newEmployee = registerEmployee.registerEmployee(name, location, passportCardNumber, phoneNumber, taxNumber);
    
	return newEmployee;
}
```
## Class Employee

```java
public class Employee (name, role, phoneNumber, email) extends Person {
}

```

## Class Agency

```java
public class Agency {
}

```

## Class Role

```java
public class Role (String Agent, String StoreManager, String NetworkManager) {
}

```

## Class Person

```java
public class Person (String name, int phoneNumber, int taxNumber, String location, String email, String passportCardNumber) {
    
      

        addPerson(person);
        
        return person;
    }
```

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





