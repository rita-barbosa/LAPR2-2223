# US 015 - List all booking requests for properties

# 4. Tests 

### ListVisitsController Tests

**Test 1:** Check if get visit requests List Works.

```java
void ensureGetVisitRequestsListWorks() {
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        AgencyRepository agencyRepository = new AgencyRepository();

        ListVisitsController controller = new ListVisitsController(authenticationRepository, agencyRepository);

        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");

        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        visits.add(visit1);

        assertEquals(visits, visits);
    }

```

### Visit Tests


**Test 1:** Check if get visit date works.

```java
@Test
    void getVisitDate() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        LocalDate date = LocalDate.of(2023, 5, 12);
        String visitDate = visit.getVisitDate().toString();
        assertEquals(date.toString(), visitDate);
    }


```

**Test 2:** Check if get visit start hour works.

```java
@Test
    void getStartHour() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(12, visit.getStartHour());
    }


```


**Test 3:** Check if get visit end hour works.

```java
 @Test
    void getEndHour() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(15, visit.getEndHour());
    }

```

**Test 4:** Check if get acceptance status works.

```java
@Test
    void getAcceptanceStatus() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(false, visit.getAcceptanceStatus());
    }


```


**Test 5:** Check if get visit id works

```java
@Test
    void getId() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        int id = 0;
        assertEquals(0, id);
    }


```

# 5. Construction (Implementation)


## Class ListVisitsController 

```java
public Optional<List<VisitDto>> getVisitRequestsList(LocalDate beginDate, LocalDate endDate) throws Exception {
        Optional <List<VisitDto>> newVisitsDtoList = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Visit>> visitList = getVisitRequestsListByAgentEmail(agentEmail, beginDate, endDate);
        if (visitList.isPresent()){
        newVisitsDtoList = VisitMapper.toDto(visitList.get());
        }
        
        return getSortedVisitRequestList(newVisitsDtoList);
        }
```


## Class Visit

```java
public Visit(LocalDate visitDate, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        this.id = counter++;
        this.startHour = startHour;
        this.endHour = endHour;
        this.visitDate = visitDate;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.acceptanceStatus = false;
        }
```

# 6. Integration and Demo 

* A new option on the Agent menu options was added.


# 7. Observations

n/a





