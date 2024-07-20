package com.andersen.hibernate.Models;

import jakarta.persistence.*;
import lombok.*;

/**
 *Implementation of the model for the table {@code traveller}.
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
 * <p>The {@code @Entity} from package {@code jakarta.persistence} generates entity of the table {@code traveller}.
 * This entity is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @Table} from package {@code jakarta.persistence} maps with the table named {@code traveller}.
 * This table is used by hibernate for further manipulations with database {@code ticketToRideDb}.
 *
 * <p>The {@code @UniqueConstraint} from package {@code jakarta.persistence} maps with the table named {@code traveller} and it's column {@code id}.
 * This annotation is used by hibernate to note the column's unique constraint.
 *
 * <p>The {@code @Id} from package {@code jakarta.persistence} maps with the table named {@code traveller} and it's column {@code id}.
 * This annotation is used by hibernate to note the column with model's identity information.
 *
 * <p>The {@code @GeneratedValue} from package {@code jakarta.persistence} is used to automatically generate unique values for primary key of table column {@code id} within it's database.
 * This annotation is used by hibernate to note the column with automatically generated value.
 * Used strategy is {@code SEQUENCE} with the name in database {@code traveller_id_seq}
 *
 * <p>The {@code @SequenceGenerator} specifies the name of the generator in the database.
 * Here name for hibernate and database is {@code traveller_id_seq} with initial value of 1 and allocation size for 1 entity.
 *
 * <p>The field {@code id} is for column {@code id} of table {@code traveller}. Denotes the identity of the entity model.
 * The field {@code id} is of type {@code Integer}.
 *
 * <p>The field {@code name} is for column {@code name} of table {@code traveller}. Denotes the name of the entity model.
 * The field {@code name} is of type {@code String}.
 *
 * <p>The field {@code moneyAmount} is for column {@code moneyAmount} of table {@code traveller}. Denotes the amount of money of the entity model.
 * The field {@code moneyAmount} is of type {@code Integer}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/hibernate/Models/package-info.html">Traveller</a>.
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
@Table(name = "traveller", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class Traveller {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="traveller_id_seq")
    @SequenceGenerator(name = "traveller_id_seq", sequenceName="traveller_id_seq",
            initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="moneyAmount")
    private Integer moneyAmount;
}
