package com.andersen.hibernate.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 *Implementation of the model for the table {@code route}.
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
 * <p>The {@code @Entity} from package {@code jakarta.persistence} generates entity of the table {@code route}.
 * This entity is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @Table} from package {@code jakarta.persistence} maps with the table named {@code route}.
 * This table is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @UniqueConstraint} from package {@code jakarta.persistence} maps with the table named {@code route} and it's column {@code id}.
 * This annotation is used by hibernate to note the column's unique constraint.
 *
 * <p>The {@code @Id} from package {@code jakarta.persistence} maps with the table named {@code route} and it's column {@code id}.
 * This annotation is used by hibernate to note the column with model's identity information.
 *
 * <p>The {@code @GeneratedValue} from package {@code jakarta.persistence} is used to automatically generate unique values for primary key of table column {@code id} within it's database.
 * This annotation is used by hibernate to note the column with automatically generated value.
 * Used strategy is {@code SEQUENCE} with the name in database {@code route_id_seq}
 *
 * <p>The {@code @SequenceGenerator} specifies the name of the generator in the database.
 * Here name for hibernate and database is {@code route_id_seq} with initial value of 1 and allocation size for 1 entity.
 *
 * <p>The field {@code id} is for column {@code id} of table {@code route}. Denotes the identity of the entity model.
 * The field {@code id} is of type {@code Integer}.
 *
 * <p>The field {@code segmentList} is for column {@code segments} of table {@code route}. Denotes the list of segments of the entity model.
 * The field {@code segmentList} is of type {@code String}.
 *
 * <p>The {@code @ManyToOne} from package {@code jakarta.persistence} is used to map relation from one city to many routes by foreign key referencing from column {@code cityA} to the field {@code cityA}.
 * The {@code @JoinColumn} denotes the foreign key column with name {@code cityA}. The field {@code ignoreUnknown} is used to ignore any unknown properties in JSON input without exception.
 * The field {@code value} is used to ignore properties of foreign table. Ignored properties are {@code "hibernateLazyInitializer", "handler", "created"}.
 * The field {@code cityA} is of type {@code City}. The foreign key {@code cityA} is referencing the table {@code city}.
 *
 * <p>The {@code @ManyToOne} from package {@code jakarta.persistence} is used to map relation from one city to many routes by foreign key referencing from column {@code cityA} to the field {@code cityB}.
 * The {@code @JoinColumn} denotes the foreign key column with name {@code cityB}. The field {@code ignoreUnknown} is used to ignore any unknown properties in JSON input without exception.
 * The field {@code value} is used to ignore properties of foreign table. Ignored properties are {@code "hibernateLazyInitializer", "handler", "created"}.
 * The field {@code cityB} is of type {@code City}. The foreign key {@code cityB} is referencing the table {@code city}.
 *
 * <p>The field {@code price} is for column {@code price} of table {@code route}. Denotes the price of the entity model.
 * The field {@code price} is of type {@code Integer}.
 *
 * <p>The field {@code boundryAmount} is for column {@code boundry_amount} of table {@code route}. Denotes the amount of boundry of the entity model.
 * The field {@code boundryAmount} is of type {@code Integer}.
 *
 * <p>The field {@code description} is for column {@code description} of table {@code route}. Denotes the description of the entity model.
 * The field {@code description} is of type {@code String}.
 *
 * <p>The field {@code currency} is for column {@code currency} of table {@code route}. Denotes the currency of the entity model.
 * The field {@code currency} is of type {@code String}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/hibernate/Models/package-info.html">Route</a>.
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
@Table(name = "route", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="route_id_seq")
    @SequenceGenerator(name = "route_id_seq", sequenceName="route_id_seq",
            initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name="segments")
    private String segmentList;

    // A model belongs to one city
    // Foreign key referencing the city table
    @ManyToOne
    @JoinColumn(name = "cityA")
    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    private City cityA;

    // A model belongs to one city
    // Foreign key referencing the city table
    @ManyToOne
    @JoinColumn(name = "cityB")
    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    private City cityB;

    @Column(name="price")
    private Integer price;

    @Column(name="boundry_amount")
    private Integer boundryAmount;

    @Column(name="description")
    private String description;

    @Column(name="currency")
    private String currency;
}
