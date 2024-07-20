package com.andersen.hibernate.Models;

import jakarta.persistence.*;
import lombok.*;

/**
 *Implementation of the model for the table {@code city}.
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
 * <p>The {@code @Entity} from package {@code jakarta.persistence} generates entity of the table {@code city}.
 * This entity is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @Table} from package {@code jakarta.persistence} maps with the table named {@code city}.
 * This table is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @UniqueConstraint} from package {@code jakarta.persistence} maps with the table named {@code city} and it's column {@code id}.
 * This annotation is used by hibernate to note the column's unique constraint.
 *
 * <p>The {@code @Id} from package {@code jakarta.persistence} maps with the table named {@code city} and it's column {@code id}.
 * This annotation is used by hibernate to note the column with model's identity information.
 *
 * <p>The {@code @GeneratedValue} from package {@code jakarta.persistence} is used to automatically generate unique values for primary key of table column {@code id} within it's database.
 * This annotation is used by hibernate to note the column with automatically generated value.
 * Used strategy is {@code SEQUENCE} with the name in database {@code city_id_seq}
 *
 * <p>The {@code @SequenceGenerator} specifies the name of the generator in the database.
 * Here name for hibernate and database is {@code city_id_seq} with initial value of 1 and allocation size for 1 entity.
 *
 * <p>The field {@code id} is for column {@code id} of table {@code city}. Denotes the identity of the entity model.
 * The field {@code id} is of type {@code Integer}.
 *
 * <p>The field {@code name} is for column {@code name} of table {@code city}. Denotes the name of the entity model.
 * The field {@code name} is of type {@code String}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/hibernate/Models/package-info.html">City</a>.
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
@Table(name = "city", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="city_id_seq")
    @SequenceGenerator(name = "city_id_seq", sequenceName="city_id_seq",
            initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name="name")
    private String name;
}
