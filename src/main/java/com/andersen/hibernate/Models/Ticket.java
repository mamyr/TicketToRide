package com.andersen.hibernate.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

/**
 *Implementation of the model for the table {@code ticket}.
 *
 * <p>The {@code @NoArgsConstructor} from package {@code lombok} generates a no-args constructor.
 * Will generate an error message if such a constructor cannot be written due to the existence of final fields.
 *
 * <p>The {@code @AllArgsConstructor} from package {@code lombok} generates an all-args constructor.
 * An all-args constructor requires one argument for every field in the class.
 *
 * <p>The {@code @Builder} from package {@code lombok} generates all getters and setters in the class to help for initialising a class instance.
 *
 * <p>The {@code @Getter} from package {@code lombok} generates all getters for fields of the class.
 *
 * <p>The {@code @Setter} from package {@code lombok} generates all setters for fields of the class.
 *
 * <p>The {@code @Entity} from package {@code jakarta.persistence} generates entity of the table {@code ticket}.
 * This entity is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @Table} from package {@code jakarta.persistence} maps with the table named {@code ticket}.
 * This table is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @UniqueConstraint} from package {@code jakarta.persistence} maps with the table named {@code ticket} and it's column {@code id}.
 * This annotation is used by hibernate to note the column's unique constraint.
 *
 * <p>The {@code @Id} from package {@code jakarta.persistence} maps with the table named {@code ticket} and it's column {@code id}.
 * This annotation is used by hibernate to note the column with model's identity information.
 *
 * <p>The {@code @GeneratedValue} from package {@code jakarta.persistence} is used to automatically generate unique values for primary key of table column {@code id} within it's database.
 * This annotation is used by hibernate to note the column with automatically generated value.
 * Used strategy is {@code SEQUENCE} with the name in database {@code ticket_id_seq}
 *
 * <p>The {@code @SequenceGenerator} specifies the name of the generator in the database.
 * Here name for hibernate and database is {@code ticket_id_seq} with initial value of 1 and allocation size for 1 entity.
 *
 * <p>The field {@code id} is for column {@code id} of table {@code ticket}. Denotes the identity of the entity model.
 * The field {@code id} is of type {@code Integer}.
 *
 * <p>The {@code @ManyToOne} from package {@code jakarta.persistence} is used to map relation from one traveller to many tickets by foreign key referencing from column {@code traveller_id} to the field {@code traveller}.
 * The {@code @JoinColumn} denotes the foreign key column with name {@code traveller_id}. The field {@code ignoreUnknown} is used to ignore any unknown properties in JSON input without exception.
 * The field {@code value} is used to ignore properties of foreign table. Ignored properties are {@code "hibernateLazyInitializer", "handler", "created"}.
 * The field {@code traveller} is of type {@code Traveller}. The foreign key {@code traveller_id} is referencing the table {@code traveller}.
 *
 * <p>The {@code @ManyToOne} from package {@code jakarta.persistence} is used to map relation from one route to many tickets by foreign key referencing from column {@code route_id} to the field {@code route}.
 * The {@code @JoinColumn} denotes the foreign key column with name {@code route_id}. The field {@code ignoreUnknown} is used to ignore any unknown properties in JSON input without exception.
 * The field {@code value} is used to ignore properties of foreign table. Ignored properties are {@code "hibernateLazyInitializer", "handler", "created"}.
 * The field {@code route} is of type {@code Route}. The foreign key {@code route_id} is referencing the table {@code route}.
 *
 * <p>The field {@code price} is for column {@code price} of table {@code ticket}. Denotes the price of the entity model.
 * The field {@code price} is of type {@code Integer}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/hibernate/Models/package-info.html">Ticket</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "ticket", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName="ticket_id_seq",
            initialValue = 1, allocationSize = 1)
    private Integer id;

    // A model belongs to one traveller
    // Foreign key referencing the traveller table
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    private Traveller traveller;

    // A model belongs to one traveller
    // Foreign key referencing the traveller table
    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    private Route route;

    @Column(name="price")
    private Integer price;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id);
    }
}
