package com.andersen.spring.dtos;

import lombok.*;

/**
 *Implementation of the model for data transfer object input for route request.
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
 * <p>The field {@code departure} denotes the departure city of the object.
 * The field {@code departure} is of type {@code String}.
 *
 * <p>The field {@code arrival} denotes the arrival city of the object.
 * The field {@code arrival} is of type {@code String}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/dtos/package-info.html">InputForRoute</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class InputForRoute {
    private String departure;
    private String arrival;
}
