# US 011 - To accept purchase orders

# 4. Tests

*Yet to be done.*

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





