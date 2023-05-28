package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListTest {

    @Test
    void ensureAddOrderWorks() {
        List<Order> orders = new ArrayList<>();
        OrderList orderList = new OrderList();
        LocalDate orderDate = LocalDate.now();
        double amount = 245000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);

        double amount1 = 245000;
        Integer id1 = 21;
        Email clientEmail1 = new Email("client@email.com");
        String acceptanceAnswer1 = "acceptance";
        Order o2 = new Order(id1, amount1, orderDate, clientEmail1, acceptanceAnswer1);

        orders.add(o1);
        orders.add(o2);
        orderList.addOrder(o1);
        orderList.addOrder(o2);

        assertEquals(orders, orderList.getList());
    }

    @Test
    void ensureDefineOrderAcceptanceWorks() {
        OrderList orderList = new OrderList();
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        String answer = "accept";
        Order o1 = new Order(amount, clientEmail);
        orderList.addOrder(o1);

        assertTrue(orderList.defineOrderAcceptance(answer, o1.getId()));
    }

    @Test
    void ensureGetListWorks() {
        List<Order> orders = new ArrayList<>();
        OrderList orderList = new OrderList();
        LocalDate orderDate = LocalDate.now();
        double amount = 245000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);

        double amount1 = 245000;
        Integer id1 = 21;
        Email clientEmail1 = new Email("client@email.com");
        String acceptanceAnswer1 = "acceptance";
        Order o2 = new Order(id1, amount1, orderDate, clientEmail1, acceptanceAnswer1);

        orders.add(o1);
        orders.add(o2);
        orderList.addOrder(o1);
        orderList.addOrder(o2);

        assertEquals(orders, orderList.getList());
    }

    @Test
    void sortOrdersByHighestOrderAmount() {
        OrderList orderList = new OrderList();
        LocalDate orderDate = LocalDate.now();
        double amount = 503000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);

        double amount1 = 245000;
        Integer id1 = 21;
        Email clientEmail1 = new Email("client@email.com");
        String acceptanceAnswer1 = "acceptance";
        Order o2 = new Order(id1, amount1, orderDate, clientEmail1, acceptanceAnswer1);

        orderList.addOrder(o2);
        orderList.addOrder(o1);

        List<Order> expected = new ArrayList<>();
        expected.add(o1);
        expected.add(o2);

        orderList.sortOrdersByHighestOrderAmount();

        assertEquals(expected, orderList.getList());
    }

    @Test
    void testEqualsSameObjects() {
        LocalDate orderDate = LocalDate.now();
        double amount = 503000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);
        assertEquals(o1, o1);
    }

    @Test
    void testEqualsDifferentObjects() {
        LocalDate orderDate = LocalDate.now();
        double amount = 503000;
        Integer id = 20;
        Email clientEmail = new Email("client@email.com");
        String acceptanceAnswer = "reject";
        Order o1 = new Order(id, amount, orderDate, clientEmail, acceptanceAnswer);
        assertNotEquals(o1, new Object());
    }

    @Test
    void testEqualsSameObjectsDifferentAttributes() {
    }

    @Test
    void testHashCodeDifferentObjects() {
    }

    @Test
    void testHashCodeSameObjects() {
    }
}