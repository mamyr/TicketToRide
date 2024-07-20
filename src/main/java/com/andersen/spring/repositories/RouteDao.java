package com.andersen.spring.repositories;

import com.andersen.hibernate.Models.Route;
import com.andersen.spring.services.RouteDaoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *Interface which extends the {@code CrudRepository} and used for the {@code Route} class from the package {@link com.andersen.hibernate.Models.Route}.
 *
 * <p>The Spring {@code @Repository} annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
 * {@code @Repository} annotates classes at the persistence layer, which will act as a database repository.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/com/andersen/spring/repositories/package-info.html">RouteDao</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Route
 * @see     RouteDaoImpl
 * @since   1.0-SNAPSHOT
 */
@Repository
public interface RouteDao extends CrudRepository<Route, Integer> {
}
