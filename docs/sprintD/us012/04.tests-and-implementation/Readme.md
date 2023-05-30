# US012 - To import legacy system information

# 4. Tests 

*Yet to be done.*

[//]: # ()
[//]: # ()
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


## Class ImportLegacyInformationController 

```java
public Boolean importInformationFromFile(String filePath) {
        boolean success;
        Optional<List<LegacySystemDto>> newList;
    
        newList = LegacySystem.importInformation(filePath);
        try {
            if (newList.isPresent()) {
                for (LegacySystemDto dto : newList.get()) {
                    Optional<Agency> newAgency = registerAgency(dto);
                    if (newAgency.isPresent()) {
                        Employee newAgent = registerAgent(newAgency.get());
                        Person newOwner = registerOwner(dto);
                        String ownerEmail = newOwner.getEmailAddress().getEmail();
                        publishAnnouncement(dto, newAgency.get(), newAgent, ownerEmail);
                    }
                }
            }
            success = true;
        } catch (Exception e) {
        success = false;
        }
        return success;
    }
```


## Class LegacySystem

```java
public static Optional<List<LegacySystemDto>> importInformation(String filePath) {
        List<LegacySystemDto> legacySystemInformationDtoList = new ArrayList<>();

        File file = new File(filePath);
        if (!verifyFile(file)) {
            return Optional.of(legacySystemInformationDtoList);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                if (attributes.length > 1) {
                    List<String> legacySystemInformationList = new ArrayList<>();
                    for (int i = 1; i < attributes.length; i++) {
                        legacySystemInformationList.add(attributes[i].trim());
                    }
                    LegacySystemDto legacySystemDto = LegacySystemMapper.toDto(legacySystemInformationList);
                    legacySystemInformationDtoList.add(legacySystemDto);
                }
            }
            return Optional.of(legacySystemInformationDtoList);
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't read information from file. ");
        }
        return Optional.empty();
}
```

# 6. Integration and Demo 

* A new option on the System Administrator menu options was added.

# 7. Observations

n/a





