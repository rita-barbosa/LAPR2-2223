# US 007 - Register user in the system

# 4. Tests 

**Test 1:** 

	@Test
    void ensureCreatePersonWorksForNonExistentPerson() {
        PersonRepository personRepository = new PersonRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        RegisterUserController ctrl = new RegisterUserController(authenticationRepository, personRepository);

        boolean result = ctrl.createPerson("Joseph", "C12345678", "123-23-4566", "joseph@email.com", "945 835 9008", "JOSep45"
                , "St. Mark's Place", "Manhattan", "East Village", "NY", "45678");
    }

   

**Test 2:**  

	 @Test
    void ensureCreatePersonWorksForExistentPerson() {
        PersonRepository personRepository = new PersonRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Employee employee = new Employee(1234, "Joseph", "C12345678", "123-23-4566", "joseph@email.com",
                "agent", "945 835 9008", "East Village", "Manhattan", "NY", "45678", "St. Mark's Place");
        personRepository.add(employee);
        RegisterUserController ctrl = new RegisterUserController(authenticationRepository, personRepository);

        boolean result = ctrl.createPerson("Joseph", "C12345678", "123-23-4566", "joseph@email.com", "945 835 9008", "JOSep45"
                , "St. Mark's Place", "Manhattan", "East Village", "NY", "45678");
    }


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class RegisterUserController 

```java
public boolean createPerson(String name, String passportCardNumber, String taxNumber, String emailAddress,
        String phoneNumber, String password, String streetName, String city, String district, String state,
        String zipcode) {

        PersonRepository personRepository = getPersonRepository();

        Person person = new Person(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, AuthenticationController.ROLE_CLIENT,
        streetName, city, district, state, zipcode);

        if (personRepository.add(person)) {
        AuthenticationRepository newAuthenticationRepository = getAuthenticationRepository();
        return newAuthenticationRepository.addUserWithRole(name, emailAddress, password, AuthenticationController.ROLE_CLIENT);
        } else {
        return true;
        }
        }
```


# 6. Integration and Demo 

* n/a


# 7. Observations

* n/a





