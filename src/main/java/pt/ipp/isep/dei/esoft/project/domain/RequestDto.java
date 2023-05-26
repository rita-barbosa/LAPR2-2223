package pt.ipp.isep.dei.esoft.project.domain;


public class RequestDto {

    private String propertyAttributes;

    private String businessAttributes;

    private static Integer id;

    private String requestDate;

    public RequestDto(String propertyAttributes, String businessAttributes, Integer id, String requestDate){
        this.propertyAttributes = propertyAttributes;
        this.businessAttributes = businessAttributes;
        this.id = id;
        this.requestDate =requestDate;
    }

    public String getPropertyAttributes(){
        return propertyAttributes;
    }

    public static Integer getRequestId(){
        return id;
    }

}
