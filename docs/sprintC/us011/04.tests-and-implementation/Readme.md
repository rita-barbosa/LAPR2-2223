# US 011 - To accept purchase orders

# 4. Tests

```java
@Test
void ensureGetAnnouncementsListWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
        AuthenticationController.ROLE_AGENT);

        List<String> roles = new ArrayList<>();
    roles.add("agent");
    Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
    authenticationRepository.doLogin("employee@this.app", "01AGEnt");

    agency.addEmployee(employee);

    String ownerEmail = "owner@email.com";
    CommissionType commissionType = new CommissionType("fixed");
    List<String> uriList = new ArrayList<>();
    uriList.add("https://www.example.com/images/photo.jpg");
    uriList.add("https://www.example.com/images/photo123.jpg");
    Property property = new Property(new PropertyType("land"), (35.5), (89.3),
    uriList, "street", "city", "district", "state", "12345");
    Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
    Email client1 = new Email("client1@this.app");
    Order o1 = new Order(23000.0, client1);
    Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
    a1.addOrder(o1);

    agency.addAnnouncement(a1);
    agencyRepository.add(agency);

    AcceptOrdersController controller = new AcceptOrdersController(agencyRepository, authenticationRepository);

    Optional<List<AnnouncementDto>> listAnnouncementsDto = controller.getAnnouncementsList();

    assertTrue(listAnnouncementsDto.isPresent());
    assertEquals(1, listAnnouncementsDto.get().size());

    }

@Test
void ensureDefineOrderAcceptanceWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
        AuthenticationController.ROLE_AGENT);

        List<String> roles = new ArrayList<>();
    roles.add("agent");
    Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
    authenticationRepository.doLogin("employee@this.app", "01AGEnt");

    agency.addEmployee(employee);

    String ownerEmail = "owner@email.com";
    CommissionType commissionType = new CommissionType("fixed");
    List<String> uriList = new ArrayList<>();
    uriList.add("https://www.example.com/images/photo.jpg");
    uriList.add("https://www.example.com/images/photo123.jpg");
    Property property = new Property(new PropertyType("land"), (35.5), (89.3),
    uriList, "street", "city", "district", "state", "12345");
    Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
    Email client1 = new Email("client1@this.app");
    Order o1 = new Order(23000.0, client1);
    Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
    a1.addOrder(o1);

    agency.addAnnouncement(a1);
    agencyRepository.add(agency);

    AcceptOrdersController controller = new AcceptOrdersController(agencyRepository, authenticationRepository);

    int announceId = a1.getId();
    int orderId = o1.getId();
    String acceptanceAnswer = "accept";

    Boolean success = controller.defineOrderAcceptance(acceptanceAnswer, announceId, orderId);
    assertTrue(success);
    }
```

# 5. Construction (Implementation)

## Class AcceptOrdersController

```java
public Optional<List<AnnouncementDto>>getAnnouncementsList(){
        Optional<List<AnnouncementDto>>newListAnnouncementsDto=Optional.empty();
        String agentEmail=getAgentEmail();
        Optional<List<Announcement>>listAnnouncements=getAnnouncementListByAgentEmail(agentEmail);
        if(listAnnouncements.isPresent()){
        newListAnnouncementsDto=AnnouncementMapper.toDto(listAnnouncements.get());
        }
        return newListAnnouncementsDto;
        }

public Boolean defineOrderAcceptance(String acceptanceAnswer,int announcementId,int orderId){
        Optional<Announcement> newAnnouncement;
        Boolean success=false;
        newAnnouncement=getAnnouncementFromDto(announcementId);
        if(newAnnouncement.isPresent()){
        success=newAnnouncement.get().defineOrderAcceptance(acceptanceAnswer,orderId);
        }
        return success;
        }

```

## Class Order

```java
public Boolean setAcceptanceAnswer(String acceptanceAnswer){
        this.acceptanceAnswer=acceptanceAnswer;
        return sendNotification(getClientEmail().toString());
        }

public Boolean rejectOrder(){
        this.acceptanceAnswer=REJECTION_ANSWER;
        return sendNotification(getClientEmail().toString());
        }


@Override
public Boolean sendNotification(String email){
        String fileName="Notifications/"+FILE_NAME+"Order"+getId()+"."+email+FILE_TYPE;
        File file=new File(fileName);
        File parentDir=file.getParentFile();
        if(!parentDir.exists()){
        parentDir.mkdirs();
        }
        try{
        FileWriter text=new FileWriter(file);
        text.write(TEXT_TO+email+"\n");
        text.write(TEXT_TOPIC+"Order Acceptance\n\n");
        text.write("The purchase order submitted in "+getOrderDate().toString()+" has been analyzed. The final decision was: "+this.acceptanceAnswer);
        text.close();
        return true;
        }catch(IOException e){
        System.out.println("ERROR: Failed to send notification.\n");
        return false;
        }
        }

```

# 6. Integration and Demo

* A new option on the Employee menu options was added.
* For demonstration purposes, some orders and announcements are bootstrapped while the system starts.

# 7. Observations

To solve the problem referred in the last sprint, the team decided to promote Collections to classes.
This led to a better distribution of responsibilities.





