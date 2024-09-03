package org.twspring.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

//VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(21) NOT NULL UNIQUE")
    @Pattern(regexp = "^\\d{21}$", message = "Account number must only have numbers and be 21 digits long")
    private String accountNumber;

    @Column(columnDefinition = "DOUBLE NOT NULL DEFAULT 0")
    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "Balance cannot be a negative number")
    private double balance;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    @NotNull(message = "Active status cannot be null")
    private boolean isActive;
//RELATIONSHIPS
    @ManyToOne
    @JsonIgnore
    private Customer customer;


}
