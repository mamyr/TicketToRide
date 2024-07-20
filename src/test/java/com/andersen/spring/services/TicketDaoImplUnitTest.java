package com.andersen.spring.services;

import com.andersen.hibernate.Models.*;
import com.andersen.spring.HibernateConf;
import com.andersen.spring.repositories.TicketDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *Implementation of the test for the service {@code TicketDaoImpl}.
 *
 *<p>The {@code @ExtendWith} is a JUnit 5 test annotation.
 *Here we extend {@code MockitoExtension}.
 *
 * <p>The {@code beforeAll, whenTicketSavedThenControlFlowAsExpected, whenTicketSavedThenControlFlowNotAsExpected, whenTicketSavedThenControlFlowEdgeExpected} operation run in O(1) time.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/services/package-info.html">TicketDaoImplUnitTest</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@ExtendWith(MockitoExtension.class)
public class TicketDaoImplUnitTest {
    /**
     * <p>The field {@code city1} is used for testing purpose. Has id = 1, name = "BIRMINGHAM"
     */
    private final City city1 = City.builder().id(1).name("BIRMINGHAM").build();
    /**
     * <p>The field {@code city2} is used for testing purpose. Has id = 2, name = "BRISTOL"
     */
    private final City city2 = City.builder().id(2).name("BRISTOL").build();
    /**
     * <p>The field {@code traveller} is used for testing purpose. Has id = 1, name = "Name1", moneyAmount = 30
     */
    private final Traveller traveller = Traveller.builder().id(1).name("Name1").moneyAmount(30).build();

    /**
     * <p>The field {@code segment} is used for testing purpose. Has id = 1, city1 = city1, city2 = city2, boundryAmount = 3, segmentPrice = 10
     */
    private final Segment segment = Segment.builder().id(1).city1(city1).city2(city2).boundryAmount(3).segmentPrice(10).build();
    /**
     * <p>The field {@code segmentIds} is used for testing purpose. Has the value {1}.
     */
    String segmentIds = "1";
    /**
     * <p>The field {@code route} is used for testing purpose. Has id = 1, cityA = city1, cityB = city2, boundryAmount = 3, segmentList = segmentIds, currency = "GBP", price = 10, description = "3 segments discount at price of 10"
     */
    private final Route route = Route.builder().id(1).cityA(city1).cityB(city2).boundryAmount(3).segmentList(segmentIds).currency("GBP").price(10).description("3 segments discount at price of 10").build();

    /**
     * <p>The field {@code ticket1} is used for testing purpose. Has id = 1, price = 10, route = route, traveller = traveller
     */
    private final Ticket ticket1 = Ticket.builder().id(1).price(10).route(route).traveller(traveller).build();

    /**
     *<p>The {@code @Spy} is used to mock the repository field {@code ticketDao} of type {@code TicketDao}.
     */
    @Spy
    private TicketDao ticketDao;

    /**
     *<p>The {@code @SpyBean} is used to mock the service field {@code ticketDaoImpl} of type {@code TicketDaoImpl}.
     */
    @SpyBean
    private static TicketDaoImpl ticketDaoImpl;

    /**
     * <p>The {@code @BeforeAll} annotation is used with method {@code beforeAll()}. The service is initialised from application context.
     */
    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(TicketDaoImplUnitTest.class);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConf.class);

        ticketDaoImpl = (TicketDaoImpl) ctx.getBean("ticketDao");
    }

    /**
     * <p>The {@code whenTicketSavedThenControlFlowAsExpected()} is implementing the scenario when saving the ticket is ended with success.
     */
    @Test
    public void whenTicketSavedThenControlFlowAsExpected() {
        Ticket ticketToSave = this.ticket1;
        Mockito.when(this.ticketDao.save(Mockito.any(Ticket.class)))
                .thenReturn(ticketToSave);

        this.ticketDaoImpl.save(ticketToSave);

        Mockito.verify(this.ticketDao, Mockito.times(1))
                .save(Mockito.any(Ticket.class));
    }

    /**
     * <p>The {@code whenTicketSavedThenControlFlowNotAsExpected()} is implementing the scenario when saving the ticket is ended with failure.
     */
    @Test
    public void whenTicketSavedThenControlFlowNotAsExpected() {
        Ticket ticketToSave = this.ticket1;
        Mockito.when(this.ticketDao.save(Mockito.any(Ticket.class)))
                .thenReturn(null); //may be duplicate restriction, nothing is saved

        this.ticketDaoImpl.save(ticketToSave);
        assertEquals(null, ticketToSave.getId());

        Mockito.verify(this.ticketDao, Mockito.times(1))
                .save(Mockito.any(Ticket.class));
    }

    /**
     * <p>The {@code whenTicketSavedThenControlFlowEdgeExpected()} is implementing the scenario when saving the ticket is ended with some exception.
     */
    @Test
    public void whenTicketSavedThenControlFlowEdgeExpected() {
        Ticket ticketToSave = null;
        Mockito.when(this.ticketDao.save(Mockito.mock(Ticket.class)))
                .thenThrow(new SQLException("No data"));

        this.ticketDaoImpl.save(ticketToSave);//null can not be saved
        assertEquals(null, ticketToSave);

        Mockito.verify(this.ticketDao, Mockito.times(1))
                .save(null);
    }

}
