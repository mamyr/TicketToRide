package com.andersen.spring.dtos;

import lombok.*;

/**
 *Implementation of the model for data transfer object output for route request.
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
 * <p>The field {@code segments} denotes the segments amount of the object.
 * The field {@code segments} is of type {@code int}.
 *
 * <p>The field {@code price} denotes the price amount of the object.
 * The field {@code price} is of type {@code int}.
 *
 * <p>The field {@code currency} denotes the price currency of the object.
 * The field {@code currency} is of type {@code String}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/com/andersen/spring/dtos/package-info.html">OutputForRoute</a>.
 *
 * @author  Zhanat Kopbayeva
 * @since   1.0-SNAPSHOT
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OutputForRoute {
    private int segments;
    private int price;
    private String currency;
}
