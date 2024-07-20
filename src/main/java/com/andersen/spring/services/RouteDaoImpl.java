package com.andersen.spring.services;

import com.andersen.hibernate.Models.City;
import com.andersen.hibernate.Models.Route;
import com.andersen.spring.repositories.CityDao;
import com.andersen.spring.repositories.RouteDao;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *Implementation of the service for the {@code Route} class from the package {@link com.andersen.hibernate.Models.Route}.
 *
 * <p>The Spring {@code @Service} annotation is used to annotate classes at the service layer.
 * Beans with {@code @Service} annotation is used to indicate that they’re holding the business logic.
 *
 * <p>The {@code setTemplate}, {@code findRouteByCities} run in O(1) time. The {@code RouteDaoImpl} constructor operation runs in constant time.
 *
 * <p>For hibernate data manipulation, it is used {@code SessionFactory} bean from spring application context.
 * The field {@code emf} is connected with the bean from application context by spring annotation {@code @Autowired} and is of type {@code SessionFactory}.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/services/package-info.html">RouteDaoImpl</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Route
 * @see     RouteDao
 * @since   1.0-SNAPSHOT
 */
@Service
public class RouteDaoImpl implements RouteDao {
    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private final Logger logger = Logger.getLogger(String.valueOf(TicketDaoImpl.class));

    @Autowired
    private SessionFactory emf;

    public RouteDaoImpl(){
    }

    /**
     * The constructor is initialised with bean {@code sessionFactory}.
     * @param sessionFactory
     */
    RouteDaoImpl(SessionFactory sessionFactory){
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
     * The method {@code findRouteByCities} returns {@code Route} object filtered by {@code City} objects' id.
     * @param integers
     * @return Route
     */
    public Route findRouteByCities(Iterable<City> integers) {
        Session session = emf.openSession();

        Iterator<City> iter = integers.iterator();
        City cityA = iter.next();
        City cityB = iter.next();
        TypedQuery<Route> query = (TypedQuery<Route>) session.createQuery("From Route where price in (select Min(r.price) as mini from Route r where (r.cityA.id in ("+cityA.getId()+","+cityB.getId()+")) and (r.cityB.id in ("+cityA.getId()+","+cityB.getId()+"))) and (cityA.id in ("+cityA.getId()+","+cityB.getId()+")) and (cityB.id in ("+cityA.getId()+","+cityB.getId()+"))");
        Route queryList = query.getSingleResult();
        return queryList;
    }

    @Override
    public <S extends Route> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Route> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Route> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Route> findAll() {
        return null;
    }

    @Override
    public Iterable<Route> findAllById(Iterable<Integer> integers) {
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
    public void delete(Route entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Route> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
