package pt.ipp.isep.dei.esoft.project.domain;

import javafx.util.Pair;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an Announcement made by a agent, based on a request.
 */
public class Announcement implements Serializable {

    private static final long serialVersionUID = -6962031503097861713L;

    /**
     * The date of acceptance for the request
     */
    private LocalDate acceptanceDate;

    /**
     * The date of acceptance for the request
     */
    private LocalDate saleDate;
    /**
     * The commission chosen by the agent associated with the announcement.
     */
    private Commission commission;
    /**
     * The request based on which the announcement is made.
     */
    private Request request;
    /**
     * The agent responsible for the announcement.
     */
    private Employee agent;

    /**
     * The identifier of an announcement.
     */
    private Integer id;
    /**
     * The OrderList that contains the orders made by clients.
     */
    private OrderList orders;

    /**
     * The list of visits.
     */
    private List<Visit> visitList;

    /**
     * The default date value.
     */
    private LocalDate DATE_BY_DEFAULT = LocalDate.of(1, 1, 1);

    /**
     * The id iteration variable.
     */
    private static int counter = 0;
    /**
     * The sale price of the deal.
     */
    private Double saleAmount;
    /**
     * The default sale amount value.
     */
    private Double SALE_AMOUNT_BY_DEFAULT = -1.0;


    /**
     * Constructs an Announcement object with the specified agent, commission type,
     * commission value, and request. Sets the acceptance date to the current date.
     *
     * @param agent           The agent responsible for the announcement
     * @param commissionType  The type of commission for the announcement
     * @param commissionValue The value of the commission for the announcement
     * @param request         The request based on which the announcement is made
     */
    public Announcement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {
        this.commission = new Commission(commissionType, commissionValue);
        this.agent = agent;
        this.request = request;
        this.saleDate = DATE_BY_DEFAULT;
        this.saleAmount = SALE_AMOUNT_BY_DEFAULT;
        this.acceptanceDate = LocalDate.now();
        this.orders = new OrderList();
        this.id = counter++;
        this.visitList = new ArrayList<>();
    }

    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, and request.
     *
     * @param agent      The agent responsible for the announcement
     * @param commission The commission for the announcement
     * @param request    The request based on which the announcement is made
     */
    public Announcement(Employee agent, Commission commission, Request request) {
        this.acceptanceDate = LocalDate.now();
        this.saleDate = DATE_BY_DEFAULT;
        this.saleAmount = SALE_AMOUNT_BY_DEFAULT;
        this.commission = commission;
        this.request = request;
        this.agent = agent;
        this.orders = new OrderList();
        this.visitList = new ArrayList<>();
        this.id = counter++;
    }


    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, request and acceptance date.
     *
     * @param agent          the agent
     * @param commission     the commission
     * @param request        the request
     * @param acceptanceDate the acceptance date
     */
    public Announcement(Employee agent, Commission commission, Request request, LocalDate acceptanceDate, OrderList orders, Integer id, Double saleAmount, LocalDate saleDate, List<Visit> visitList) {
        this.commission = commission;
        this.agent = agent;
        this.request = request;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
        this.acceptanceDate = acceptanceDate;
        this.orders = orders;
        this.visitList = visitList;
        this.id = id;
    }

    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, request and acceptance date.
     *
     * @param agent           the agent
     * @param commissionValue the commission value
     * @param request         the request
     */
    public Announcement(Employee agent, double commissionValue, Request request, String propertyDateSale, Double propertySalePrice) {
        this.commission = new Commission(new CommissionType("fixed"), commissionValue);
        this.request = request;
        this.agent = agent;
        this.acceptanceDate = DATE_BY_DEFAULT;
        int[] date = mapStringToLocalDate(propertyDateSale);
        this.saleDate = LocalDate.of(date[2], date[1], date[0]);
        this.orders = new OrderList();
        this.visitList = new ArrayList<>();
        this.saleAmount = propertySalePrice;
        this.id = counter++;
    }

    /**
     * Maps string to an array, with the day,month and year.
     *
     * @param value the value
     * @return the int [ ]
     */
    private int[] mapStringToLocalDate(String value) {
        String[] dateString = value.split("/");

        int[] date = new int[dateString.length];
        try {
            for (int i = 0; i < dateString.length; i++) {
                date[i] = Integer.parseInt(dateString[i]);
            }
        } catch (NumberFormatException e) {
            throw e;
        }
        return date;
    }

    /**
     * This method returns the characteristics of the commission.
     *
     * @return the request characteristics.
     */
    public String getCommissionAttributes() {
        return this.commission.toString();
    }

    /**
     * This method returns the characteristics of the request.
     *
     * @return the request characteristics.
     */
    public String getRequestAttributes() {
        return this.request.toString();
    }

    /**
     * This method returns the acceptance date of the announcement.
     *
     * @return The acceptance date of the announcement
     */
    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    /**
     * This method returns the ID of the announcement.
     *
     * @return the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns a list of the orders made by the clients.
     *
     * @return list of orders
     */
    public List<Order> getListOfOrders() {
        return this.orders.getList();
    }

    /**
     * This method returns the announcement OrderList instance.
     *
     * @return orderList
     */
    public Optional<OrderList> getOrderList() {
        return Optional.of(this.orders);
    }

    /**
     * This method returns the request based on which the announcement is made.
     *
     * @return The request based on which the announcement is made
     */
    public Request getRequest() {
        return request;
    }

    /**
     * This method returns a string with the agent email.
     *
     * @return agent email
     */
    public String getAgentEmail() {
        return this.agent.getEmailAddress().getEmail();
    }


    /**
     * This method returns a string with the agent name.
     *
     * @return agent name
     */
    public String getAgentName() {
        return this.agent.getName();
    }

    public Integer getAgencyId() {
        return this.agent.getAgencyId();
    }

    /**
     * This method returns a string with the agent phone number.
     *
     * @return agent phone number
     */
    public String getAgentPhoneNumber() {
        return this.agent.getPhoneNumber();
    }


    /**
     * This method adds an order to the existent OrderList.
     *
     * @param order - order intended to be added.
     * @return {@code true} if the order was successfully added; {@code false} otherwise;
     */
    public Boolean addOrder(Order order) {
        return orders.addOrder(order);
    }

    /**
     * This method checks if the agent responsible for the announcement has the specified email.
     *
     * @param agentEmail - the email to be verified
     * @return {@code true} if the agent responsible for the announcement has the specified email;{@code false} otherwise;
     */
    public Boolean hasAgentWithEmail(String agentEmail) {
        return this.getAgentEmail().equals(agentEmail);
    }

    /**
     * This method checks if the announcement has the specified id.
     *
     * @param announcementId
     * @return {@code true} if the announcement has the specified id;{@code false} otherwise;
     */
    public Boolean hasId(Integer announcementId) {
        return this.getId().equals(announcementId);
    }

    /**
     * Defines the acceptance status of an order.
     *
     * @param answer  The acceptance answer
     * @param orderId The ID of the order
     * @return {@code true} if the order acceptance was successfully defined; {@code false} otherwise
     */
    public Boolean defineOrderAcceptance(String answer, int orderId) {
        if (answer.equalsIgnoreCase("accept")) {
            this.saleDate = LocalDate.now();
            Optional<Order> order = orders.getOrderById(orderId);
            order.ifPresent(value -> this.saleAmount = value.getOrderAmount());
        }
        return orders.defineOrderAcceptance(answer, orderId);
    }


    /**
     * Creates a new copy of this Announcement object.
     *
     * @return A new copy of this Announcement object
     */
    public Announcement clone() {
        return new Announcement(this.agent, this.commission, this.request, this.acceptanceDate, this.orders, this.id, this.saleAmount, this.saleDate, this.visitList);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        if (Objects.equals(this.saleAmount, SALE_AMOUNT_BY_DEFAULT)) {
            return getRequest().toString() + String.format("Acceptance Date: %s\n", acceptanceDate);
        } else if (this.acceptanceDate.equals(DATE_BY_DEFAULT)) {
            return getRequest().toString() + String.format("Sale Date: %s\nSale Amount: %s\n", saleDate, saleAmount);
        } else {
            return getRequest().toString() + String.format("Acceptance Date: %s\nSale Date: %s\nSale Amount: %s\n", acceptanceDate, saleDate, saleAmount);
        }
    }

    /**
     * Create visit optional.
     *
     * @param visitDay        the visit day
     * @param visitMonth      the visit month
     * @param visitYear       the visit year
     * @param startHour       the start hour
     * @param endHour         the end hour
     * @param userName        the user's name
     * @param userPhoneNumber the user's phone number
     * @return the visit
     */
    public Pair<Optional<Visit>, Integer> createVisit(Integer visitDay, Integer visitMonth, Integer visitYear, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        Optional<Visit> optionalValue = Optional.empty();

        Visit visit = new Visit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (addVisit(visit).getValue()) {
            optionalValue = Optional.of(visit);
        }
        return new Pair<>(optionalValue, addVisit(visit).getKey());
    }

    /**
     * Add visit boolean.A
     *
     * @param visit the visit
     * @return the boolean
     */
    public Pair<Integer, Boolean> addVisit(Visit visit) {
        if (!validateVisit(visit).getValue()) {
            return new Pair<>(validateVisit(visit).getKey(), false);
        } else {
            this.visitList.add(visit.clone());
            return new Pair<>(validateVisit(visit).getKey(), true);
        }
    }

    /**
     * Validate visit boolean.
     *
     * @param createdVisit the created visit
     * @return the boolean
     */
    private Pair<Integer, Boolean> validateVisit(Visit createdVisit) {
        for (Visit visit : this.visitList) {
            if (visit.equals(createdVisit)) {
                return new Pair<>(1, false);
            }
            if (visit.getVisitDate().equals(createdVisit.getVisitDate())) {
                if (createdVisit.getStartHour() > visit.getStartHour() && createdVisit.getStartHour() < visit.getEndHour()) {
                    return new Pair<>(2, false);
                }
                if (createdVisit.getEndHour() > visit.getStartHour() && createdVisit.getEndHour() < visit.getEndHour()) {
                    return new Pair<>(3, false);
                }
            }
        }
        return new Pair<>(0, true);
    }

    /**
     * This method sends an sms to the client warning them that the property is available of sale.
     *
     * @return {@code true} if sms was sent successfully;{@code false} otherwise;
     */
    public Boolean sendSMS() {
        SmsNotification sms = new SmsNotification();
        String location = this.getRequest().getProperty().getLocation().toString();
        String message = "The property located in " + location +
                " became available for purchase in " + this.getAcceptanceDate() + ".";
        double[][] array = new double[5][5];

        return sms.sendNotification(this.getAgentName(), this.getAgentPhoneNumber(), message);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare
     * @return {@code true} if this object is the same as the o argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(acceptanceDate, that.acceptanceDate) && Objects.equals(saleDate, that.saleDate) && Objects.equals(commission, that.commission) && Objects.equals(request, that.request) && Objects.equals(agent, that.agent) && Objects.equals(id, that.id) && Objects.equals(orders, that.orders) && Objects.equals(visitList, that.visitList) && Objects.equals(saleAmount, that.saleAmount);
    }

    /**
     * Returns the hash code of the Announcement instance.
     *
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(acceptanceDate, saleDate, commission, request, agent, id, orders, visitList, saleAmount);
    }

    /**
     * This method returns the sale amount value.
     *
     * @return sale amount
     */
    public Double getSaleAmount() {
        return saleAmount;
    }

    /**
     * This method checks if the announcement is fit for an analysis. It
     * checks if its sale deal of a residence (apartment or house).
     *
     * @return {@code true} if its sale deal of a residence (apartment or house); {@code false} otherwise;
     */
    public boolean validForAnalysis() {
        return this.isDeal() && this.request.isSaleResidence();
    }

    /**
     * Checks if the announcement is a deal.
     *
     * @return {@code true} if an announcement is a deal; {@code false} otherwise;
     */
    public boolean isDeal() {
        return !this.saleAmount.equals(-1.0);
    }

    /**
     * Returns all the deals data of an announcement necessary for the regression model.
     *
     * @param regressionModelType - the type of regression model
     * @param variable            - the variable of the wanted data if the regression model type is simple linear.
     * @return a list with all the deals data of an announcement.
     */
    public List<Double> getDataForRegression(RegressionModelType regressionModelType, String variable) {
        List<Double> data = new ArrayList<>();

        data.add(this.getSaleAmount());
        if (regressionModelType.isSimpleLinear()) {
            data.add(this.request.getVariableValueByDesignation(variable));
        } else if (regressionModelType.isMultilinear()) {
            data.addAll(this.request.getVariablesValue());
        }
        return data;
    }

    /**
     * Gets sale date.
     *
     * @return the sale date
     */
    public LocalDate getSaleDate() {
        return this.saleDate;
    }

    /**
     * Get the visit list.
     *
     * @return the list
     */
    public List<Visit> getVisitList() {
        return this.visitList;
    }

    private void writeObject(ObjectOutputStream opst) throws IOException {
        opst.writeObject(this.orders.getList());
        opst.writeInt(this.id);
        opst.writeObject(this.agent);
        opst.writeObject(this.request);
        opst.writeObject(this.acceptanceDate);
        opst.writeObject(this.commission);
        opst.writeDouble(this.saleAmount);
        opst.writeObject(this.saleDate);
        opst.writeObject(this.visitList);
        opst.writeDouble(this.SALE_AMOUNT_BY_DEFAULT);
        opst.writeObject(this.DATE_BY_DEFAULT);
    }


    private void readObject(ObjectInputStream ipst) throws IOException, ClassNotFoundException {
        this.orders = new OrderList((List<Order>) ipst.readObject());
        this.id =  ipst.readInt();
        this.agent = (Employee) ipst.readObject();
        this.request = (Request) ipst.readObject();
        this.acceptanceDate = (LocalDate) ipst.readObject();
        this.commission = (Commission) ipst.readObject();
        this.saleAmount =  ipst.readDouble();
        this.saleDate = (LocalDate) ipst.readObject();
        this.visitList = (ArrayList<Visit>) ipst.readObject();
        this.SALE_AMOUNT_BY_DEFAULT = ipst.readDouble();
        this.DATE_BY_DEFAULT = (LocalDate) ipst.readObject();
    }
}



