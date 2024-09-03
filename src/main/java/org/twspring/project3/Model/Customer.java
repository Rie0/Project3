package org.twspring.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    //VARIABLES
    @Id
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL UNIQUE")
    private String phoneNumber;

    //RELATIONSHIPS
    @OneToOne
    @MapsId
    private User user;
}
