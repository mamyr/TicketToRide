package com.andersen.spring.repositories;

import com.andersen.hibernate.Models.City;
import com.andersen.spring.services.CityDaoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *Interface which extends the {@code CrudRepository} and used for the {@code City} class from the package {@link com.andersen.hibernate.Models.City}.
 *
 * <p>The Spring {@code @Repository} annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
 * {@code @Repository} annotates classes at the persistence layer, which will act as a database repository.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/com/andersen/spring/repositories/package-info.html">CityDao</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     City
 * @see     CityDaoImpl
 * @since   1.0-SNAPSHOT
 */
@Repository
public interface CityDao extends CrudRepository<City, Integer> {
}
