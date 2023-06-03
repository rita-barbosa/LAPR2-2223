# US012 - To import legacy system information

# 4. Tests 

**Test 1:** Check that it is possible to create a legacyDto from an array of attributes gotten from the legacy system csv file.

```java
    @Test
    void toDto() {
        List<String> legacySystemInformationList = null;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        assertEquals(legacyDto, LegacySystemMapper.toDto(legacySystemInformationList));
    }
```

**Test 2:** Check that it is possible to create an agency from an array of attributes gotten from the legacy system csv file.

```java
    @Test
    void toModelAgency() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        Location location = new Location("71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705");
        Agency expected = new Agency(1, "North Pole", "northpole@realstateUSA.com", "907-488-4800", location);

        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.toModelAgency(legacyDto));
    }
```

**Test 3:** Check that it is possible to create a person from an array of attributes gotten from the legacy system csv file.

```java
    @Test
    void toModelPerson() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        Person expected = new Person("Georgia PEDDIE", "111-11-0000", "907-488-1419", "GeorgiaPEDDIE2155@gmail.com", "711111111");

        assert legacyDto != null;
        Person actual = LegacySystemMapper.toModelPerson(legacyDto);

        actual.setPassportCardNumber("711111111");

        assertEquals(expected, actual);
    }
```

**Test 4:** Check that it is possible to create a request from an array of attributes gotten from the legacy system csv file.

```java
    @Test
    void toModelRequest() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        String location = "449 N Santa Claus Lane, North Pole, AK, 99705";

        Request expected = new Request("sale", "NA", (double) 208500, "house",
                (double) 1710, location, (double) 29, "3", "4", "2", "Y", "Y",
                "Y", "Y", "S", "08-01-2001");

        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.toModelRequest(legacyDto));
    }
```

**Test 5:** Check that it is possible to get the commision value of a legacyDto created from the legacy system csv file information.

```java
    @Test
    void getCommissionValue() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        Double expected = (double) 5;


        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.getCommissionValue(legacyDto));
    }
```

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





