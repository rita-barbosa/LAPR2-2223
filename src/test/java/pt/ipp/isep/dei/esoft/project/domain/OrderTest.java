package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void ensureGetOrderAmountWorks() {
        double amount = 245000;
        Order o1 = new Order(amount, new Email("client@email.com"));

        assertEquals(amount, o1.getOrderAmount());
    }

    @Test
    void ensureGetOrderDateWorks() {
        LocalDate orderDate = LocalDate.now();
        double amount = 245000;
        Order o1 = new Order(amount, new Email("client@email.com"));

        assertEquals(orderDate, o1.getOrderDate());
    }

    @Test
    void ensureGetClientEmailWorks() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);

        assertEquals(clientEmail, o1.getClientEmail());
    }

    @Test
    void ensureGetIdWorks() {
        LocalDate orderDate = LocalDate.now();
        double amount = 245000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);

        assertEquals(id, o1.getId());
    }

    @Test
    void ensureGetAcceptanceAnswerWorks() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        assertEquals(acceptanceAnswer, o1.getAcceptanceAnswer());

    }

    @Test
    void ensureHasIdWorks() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);

        assertTrue(o1.hasId(o1.getId()));
    }

    @Test
    void setAcceptanceAnswer() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        assertTrue(o1.setAcceptanceAnswer(acceptanceAnswer));
    }

    @Test
    void ensureRejectOrderWorks() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);

        assertTrue(o1.rejectOrder());
        assertEquals("reject", o1.getAcceptanceAnswer());

    }

    @Test
    void ensureSendNotificationWorks() {
        Double orderAmount = 100.0;
        Email clientEmail = new Email("example@example.com");
        Order order = new Order(orderAmount, clientEmail);

        String email = clientEmail.toString();
        Boolean notificationSent = order.sendNotification(email);

        assertTrue(notificationSent);
    }

    @Test
    void ensureTestCloneWorks() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        Order clone = o1.clone();

        assertEquals(o1, clone);
    }

    @Test
    void testEqualsSameObjects() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        assertEquals(o1, o1);
    }

    @Test
    void testEqualsDifferentObjects() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        assertNotEquals(o1, new Object());
    }

    @Test
    void testEqualsSameObjectsDifferentAttributes() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        double amount1 = 3245000;
        Email clientEmail1 = new Email("client1@email.com");
        Order o2 = new Order(amount1, clientEmail1);
        String acceptanceAnswer1 = "accept";
        o2.setAcceptanceAnswer(acceptanceAnswer1);

        assertNotEquals(o1, o2);
    }

    @Test
    void testHashCodeDifferentObjects() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        double amount1 = 3245000;
        Email clientEmail1 = new Email("client1@email.com");
        Order o2 = new Order(amount1, clientEmail1);
        String acceptanceAnswer1 = "accept";
        o2.setAcceptanceAnswer(acceptanceAnswer1);

        assertNotEquals(o1.hashCode(), o2.hashCode());
    }


    @Test
    void testHashCodeSameObject() {
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        assertEquals(o1, o1);
    }
}