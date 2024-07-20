package com.andersen.spring.services;

import com.andersen.hibernate.Models.Route;
import com.andersen.hibernate.Models.Ticket;
import com.andersen.spring.repositories.RouteDao;
import com.andersen.spring.repositories.TicketDao;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 *Implementation of the service for the {@code Ticket} class from the package {@link com.andersen.hibernate.Models.Ticket}.
 *
 * <p>The Spring {@code @Service} annotation is used to annotate classes at the service layer.
 * Beans with {@code @Service} annotation is used to indicate that they’re holding the business logic.
 *
 * <p>The {@code setTemplate}, {@code save} run in O(1) time. The {@code TicketDaoImpl} constructor operation runs in constant time.
 *
 * <p>For hibernate data manipulation, it is used {@code SessionFactory} bean from spring application context.
 * The field {@code emf} is connected with the bean from application context by spring annotation {@code @Autowired} and is of type {@code SessionFactory}.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/services/package-info.html">TicketDaoImpl</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Ticket
 * @see     TicketDao
 * @since   1.0-SNAPSHOT
 */
@Service
public class TicketDaoImpl implements TicketDao {
    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private final Logger logger = Logger.getLogger(String.valueOf(TicketDaoImpl.class));

    @Autowired
    private SessionFactory emf;

    public TicketDaoImpl(){
    }

    /**
     * The constructor is initialised with bean {@code sessionFactory}.
     * @param sessionFactory
     */
    TicketDaoImpl(SessionFactory sessionFactory){
        this.emf = sessionFactory;
    }

    /**
     * The method {@code setTemplate} is used for explicit setting of bean {@code sessionFactory}.
     * @param sessionFactory
     */
    public void setTemplate(SessionFactory sessionFactory) {
        this.emf = sessionFactory;
    }

    /**
     * The annotation {@code @Override} is used to show the method is overriden from {@code CityDao} interface.
     * If object is not saved the transaction is rolled back and object is returned as is.
     * The method {@code save} is used to save object {@code Ticket} to database using hibernate session.
     * @param ticket
     * @return
     * @param <S extends Ticket>
     */
    @Override
    public <S extends Ticket> S save(S ticket) {
        EntityTransaction newTransaction = null;
        Session session = emf.openSession();
        Transaction t = session.beginTransaction();

        try {
            if (ticket.getId() == null) {
                session.save(ticket);
            }
        } catch (Exception ex) {
            logger.severe("Problem saving individual ticket <{}>"+ticket.getId());
            logger.severe(ex.getMessage());
            t.rollback();
        } finally {
            t.commit();
        }
        logger.info("Ticket is saved: {}"+ticket.getId());
        session.close();
        return ticket;
    }

    @Override
    public <S extends Ticket> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Ticket> findAll() {
        return null;
    }

    @Override
    public Iterable<Ticket> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Ticket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ticket> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
