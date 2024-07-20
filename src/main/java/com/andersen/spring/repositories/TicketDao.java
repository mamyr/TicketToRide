package com.andersen.spring.repositories;

import com.andersen.hibernate.Models.Ticket;
import com.andersen.spring.services.TicketDaoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *Interface which extends the {@code CrudRepository} and used for the {@code Ticket} class from the package {@link com.andersen.hibernate.Models.Ticket}.
 *
 * <p>The Spring {@code @Repository} annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
 * {@code @Repository} annotates classes at the persistence layer, which will act as a database repository.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/com/andersen/spring/repositories/package-info.html">TicketDao</a>.
 *
 * @author  Zhanat Kopbayeva
 * @see     Ticket
 * @see     TicketDaoImpl
 * @since   1.0-SNAPSHOT
 */
@Repository
public interface TicketDao extends CrudRepository<Ticket, Integer> {
}
