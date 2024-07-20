package com.andersen.spring.services;

import com.andersen.hibernate.Models.City;
import com.andersen.spring.dtos.InputForRoute;
import com.andersen.spring.repositories.CityDao;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *Implementation of the service for the {@code City} class from the package {@link com.andersen.hibernate.Models.City}.
 *
 * <p>The Spring {@code @Service} annotation is used to annotate classes at the service layer.
 * Beans with {@code @Service} annotation is used to indicate that they’re holding the business logic.
 *
 * <p>The {@code setTemplate}, {@code getCityIdListByNameList} run in O(1) time. The {@code CityDaoImpl} constructor operation runs in constant time.
 *
 * <p>For hibernate data manipulation, it is used {@code SessionFactory} bean from spring application context.
 * The field {@code emf} is connected with the bean from application context by spring annotation {@code @Autowired} and is of type {@code SessionFactory}.
 *
 * <p><strong>Note that this implementation is not final.</strong>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/services/package-info.html">CityDaoImpl</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     City
 * @see     CityDao
 * @since   1.0-SNAPSHOT
 */
@Service
public class CityDaoImpl implements CityDao {
    /**
     * The {@code Logger} is used for message logging by default on console.
     */
    private final Logger logger = Logger.getLogger(String.valueOf(CityDaoImpl.class));

    @Autowired
    private SessionFactory emf;

    public CityDaoImpl(){
    }

    /**
     * The constructor is initialised with bean {@code sessionFactory}.
     * @param sessionFactory
     */
    CityDaoImpl(SessionFactory sessionFactory){
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
     * The method {@code getCityIdListByNameList} returns {@code City} objects filtered by city names from {@code InputForRoute} object.
     * @param input
     * @return ArrayList<City>
     */
    public ArrayList<City> getCityIdListByNameList(InputForRoute input){
        String cityNames = "";
        cityNames += "'"+input.getArrival().toUpperCase() + "', '"+input.getDeparture().toUpperCase()+"'";

        Session session = emf.openSession();

        TypedQuery<City> query = (TypedQuery<City>) session.createQuery("From City Where name in ("+cityNames+")");
        ArrayList<City> list = new ArrayList<City>();
        List queryList = query.getResultList();
        for(Object o:queryList){
            City obj = (City) o;
            list.add(obj);
        }
        return list;
    }

    @Override
    public <S extends City> S save(S entity) {
        return null;
    }

    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<City> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<City> findAll() {
        return null;
    }

    @Override
    public Iterable<City> findAllById(Iterable<Integer> integers) {
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
    public void delete(City entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends City> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
