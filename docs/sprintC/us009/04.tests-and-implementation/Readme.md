# US 009 - Schedule a visit to a property

# 4. Tests 

*Yet to be done.*

[//]: # (**Test 1:** Check that it is not possible to create an instance of the Task class with null values. )

[//]: # ()
[//]: # (	@Test&#40;expected = IllegalArgumentException.class&#41;)

[//]: # (		public void ensureNullIsNotAllowed&#40;&#41; {)

[//]: # (		Task instance = new Task&#40;null, null, null, null, null, null, null&#41;;)

[//]: # (	})

[//]: # (	)
[//]: # ()
[//]: # (**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. )

[//]: # ()
[//]: # (	@Test&#40;expected = IllegalArgumentException.class&#41;)

[//]: # (		public void ensureReferenceMeetsAC2&#40;&#41; {)

[//]: # (		Category cat = new Category&#40;10, "Category 10"&#41;;)

[//]: # (		)
[//]: # (		Task instance = new Task&#40;"Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat&#41;;)

[//]: # (	})


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class ScheduleVisitController 

```java
public Boolean scheduleVisit(Announcement announcement, Integer startHour, Integer endHour,
        Integer visitDay, Integer visitMonth, Integer visitYear) {
        Optional<Person> user = getUserPerson();
        String userName = "";
        String userPhoneNumber = "";

        if (user.isPresent()) {
            userName = user.get().getName();
            userPhoneNumber = user.get().getPhoneNumber();
        }

        Optional<Visit> newVisit = announcement.createVisit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (newVisit.isPresent()) {
            String agentEmail = announcement.getAgentEmail();
            newVisit.get().sendNotification(agentEmail);
        }
        return newVisit.isPresent();
    }
```


## Class Announcement

```java
public Optional<Visit> createVisit(Integer visitDay, Integer visitMonth, Integer visitYear, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        Optional<Visit> optionalValue = Optional.empty();

        Visit visit = new Visit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (addVisit(visit)) {
            optionalValue = Optional.of(visit);
        }
        return optionalValue;
}
```

# 6. Integration and Demo 

* A new option on the Client menu options was added.

* Some demo purposes some announcements are bootstrapped while system starts.


# 7. Observations

To solve the problem referred in the last sprint, the team decided to promote Collections to classes.
This led to a better distribution of responsibilities.